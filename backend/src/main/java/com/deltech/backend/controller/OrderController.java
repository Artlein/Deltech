package com.deltech.backend.controller;

import com.deltech.backend.dto.OrderRequest;
import com.deltech.backend.entity.Order;
import com.deltech.backend.security.UserDetailsImpl;
import com.deltech.backend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/my")
    public ResponseEntity<List<Order>> getMyOrders(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(orderService.getCustomerOrders(userDetails.getId()));
    }

    @PostMapping("/checkout")
    public ResponseEntity<?> checkout(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody OrderRequest request) {
        try {
            Order order = orderService.checkout(userDetails.getId(), request.getNoteCustomer());
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
