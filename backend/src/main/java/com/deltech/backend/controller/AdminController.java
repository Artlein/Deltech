package com.deltech.backend.controller;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Order;
import com.deltech.backend.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD', 'STANDARD_ADMIN')")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Object>> getDashboardStats() {
        return ResponseEntity.ok(adminService.getDashboardStats());
    }

    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(adminService.getAllCustomers());
    }

    @PutMapping("/customers/{id}/verify")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD')")
    public ResponseEntity<?> verifyCustomer(
            @PathVariable Long id,
            @RequestParam boolean isVerified) {
        try {
            adminService.verifyCustomer(id, isVerified);
            return ResponseEntity.ok("Customer verification status updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(adminService.getAllOrders());
    }

    @PutMapping("/orders/{id}/status")
    public ResponseEntity<?> updateOrderStatus(
            @PathVariable Long id,
            @RequestParam String status) {
        try {
            adminService.updateOrderStatus(id, status);
            return ResponseEntity.ok("Order status updated successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
