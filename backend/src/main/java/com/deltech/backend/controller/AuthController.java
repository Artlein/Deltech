package com.deltech.backend.controller;

import com.deltech.backend.dto.*;
import com.deltech.backend.entity.Customer;
import com.deltech.backend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/customer/login")
    public ResponseEntity<AuthResponse> loginCustomer(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.loginCustomer(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(AuthResponse.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/admin/login")
    public ResponseEntity<AuthResponse> loginAdmin(@RequestBody LoginRequest request) {
        try {
            return ResponseEntity.ok(authService.loginAdmin(request));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(AuthResponse.builder().message(e.getMessage()).build());
        }
    }

    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomer(@RequestBody RegisterRequest request) {
        try {
            Customer customer = authService.registerCustomer(request);
            return ResponseEntity.ok(customer);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        try {
            authService.forgotPassword(request.getEmail());
            return ResponseEntity.ok("If the email is registered, a password reset link has been sent.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        try {
            authService.resetPassword(request.getToken(), request.getNewPassword());
            return ResponseEntity.ok("Password has been reset successfully.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
