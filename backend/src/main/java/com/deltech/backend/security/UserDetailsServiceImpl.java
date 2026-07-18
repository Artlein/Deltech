package com.deltech.backend.security;

import com.deltech.backend.entity.Admin;
import com.deltech.backend.entity.Customer;
import com.deltech.backend.repository.AdminRepository;
import com.deltech.backend.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Try customer first
        Optional<Customer> customerOpt = customerRepository.findByUsername(username);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return new UserDetailsImpl(
                    customer.getId(),
                    customer.getUsername(),
                    customer.getPassword(),
                    customer.getEmailCustomer(),
                    customer.getNameCustomer(),
                    "CUSTOMER",
                    customer.getIsVerified(),
                    false
            );
        }

        // Try admin second
        Optional<Admin> adminOpt = adminRepository.findByUsername(username);
        if (adminOpt.isPresent()) {
            Admin admin = adminOpt.get();
            return new UserDetailsImpl(
                    admin.getId(),
                    admin.getUsername(),
                    admin.getPassword(),
                    admin.getEmail(),
                    admin.getFullname(),
                    admin.getRole().getRoleName().contains("Super") ? "SUPER_ADMIN" : 
                    admin.getRole().getRoleName().contains("Lead") ? "ADMIN_LEAD" : "STANDARD_ADMIN",
                    admin.getStatus().equals("active"),
                    true
            );
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
