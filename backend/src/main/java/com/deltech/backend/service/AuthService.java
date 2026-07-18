package com.deltech.backend.service;

import com.deltech.backend.dto.*;
import com.deltech.backend.entity.Admin;
import com.deltech.backend.entity.Admin2fa;
import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Customer2fa;
import com.deltech.backend.repository.*;
import com.deltech.backend.security.JwtUtil;
import com.deltech.backend.security.UserDetailsImpl;
import com.deltech.backend.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private Customer2faRepository customer2faRepository;

    @Autowired
    private Admin2faRepository admin2faRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TwoFactorService twoFactorService;

    @Autowired
    private EmailService emailService;

    public AuthResponse loginCustomer(LoginRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        UserDetailsImpl customDetails = (UserDetailsImpl) userDetails;

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (!customDetails.isVerified()) {
            throw new RuntimeException("Your account review is ongoing, please wait.");
        }

        Optional<Customer2fa> c2faOpt = customer2faRepository.findByCustomerId(customDetails.getId());
        if (c2faOpt.isPresent() && c2faOpt.get().getIs2faEnabled()) {
            if (request.getOtp() == null || request.getOtp().isEmpty()) {
                return AuthResponse.builder()
                        .is2faRequired(true)
                        .message("2FA code required")
                        .build();
            }
            boolean isValid = twoFactorService.verifyCode(c2faOpt.get().getOtpSecret(), request.getOtp());
            if (!isValid) {
                throw new RuntimeException("Invalid OTP code");
            }
        }

        String jwt = jwtUtil.generateToken(userDetails);
        return AuthResponse.builder()
                .token(jwt)
                .id(customDetails.getId())
                .username(customDetails.getUsername())
                .fullname(customDetails.getFullname())
                .role(customDetails.getRole())
                .is2faRequired(false)
                .build();
    }

    public AuthResponse loginAdmin(LoginRequest request) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        UserDetailsImpl customDetails = (UserDetailsImpl) userDetails;

        if (!passwordEncoder.matches(request.getPassword(), userDetails.getPassword())) {
            throw new RuntimeException("Invalid username or password");
        }

        if (!customDetails.isVerified()) {
            throw new RuntimeException("Your admin account is inactive.");
        }

        Optional<Admin2fa> a2faOpt = admin2faRepository.findByAdminId(customDetails.getId());
        if (a2faOpt.isPresent() && a2faOpt.get().getIs2faEnabled()) {
            if (request.getOtp() == null || request.getOtp().isEmpty()) {
                return AuthResponse.builder()
                        .is2faRequired(true)
                        .message("2FA code required")
                        .build();
            }
            boolean isValid = twoFactorService.verifyCode(a2faOpt.get().getOtpSecret(), request.getOtp());
            if (!isValid) {
                throw new RuntimeException("Invalid OTP code");
            }
        }

        String jwt = jwtUtil.generateToken(userDetails);
        return AuthResponse.builder()
                .token(jwt)
                .id(customDetails.getId())
                .username(customDetails.getUsername())
                .fullname(customDetails.getFullname())
                .role(customDetails.getRole())
                .is2faRequired(false)
                .build();
    }

    public Customer registerCustomer(RegisterRequest request) {
        if (customerRepository.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }
        if (customerRepository.existsByEmailCustomer(request.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Customer customer = Customer.builder()
                .nameCustomer(request.getName())
                .username(request.getUsername())
                .emailCustomer(request.getEmail())
                .phoneCustomer(request.getPhone())
                .address(request.getAddress())
                .password(passwordEncoder.encode(request.getPassword()))
                .isVerified(false) // Needs admin review
                .build();

        return customerRepository.save(customer);
    }

    public void forgotPassword(String email) {
        Optional<Customer> customerOpt = customerRepository.findByEmailCustomer(email);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            String token = UUID.randomUUID().toString();
            customer.setResetToken(token);
            customerRepository.save(customer);

            String resetUrl = "http://localhost:5173/reset-password?token=" + token;
            emailService.sendEmail(email, "Reset Password Request", 
                    "To reset your password, click the link: " + resetUrl);
            return;
        }

        Optional<Admin> adminOpt = adminRepository.findByEmail(email);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            String token = UUID.randomUUID().toString();
            admin.setResetToken(token);
            adminRepository.save(admin);

            String resetUrl = "http://localhost:5173/reset-password?token=" + token;
            emailService.sendEmail(email, "Reset Password Request", 
                    "To reset your password, click the link: " + resetUrl);
        }
    }

    public void resetPassword(String token, String newPassword) {
        Optional<Customer> customerOpt = customerRepository.findByResetToken(token);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            customer.setPassword(passwordEncoder.encode(newPassword));
            customer.setResetToken(null);
            customerRepository.save(customer);
            return;
        }

        Optional<Admin> adminOpt = adminRepository.findByResetToken(token);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            admin.setPassword(passwordEncoder.encode(newPassword));
            admin.setResetToken(null);
            adminRepository.save(admin);
            return;
        }

        throw new RuntimeException("Invalid or expired token");
    }
}
