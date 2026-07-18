package com.deltech.backend.controller;

import com.deltech.backend.dto.CartItemRequest;
import com.deltech.backend.entity.UserCart;
import com.deltech.backend.security.UserDetailsImpl;
import com.deltech.backend.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping
    public ResponseEntity<List<UserCart>> getCart(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(cartService.getCartItems(userDetails.getId()));
    }

    @PostMapping("/add")
    public ResponseEntity<UserCart> addToCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.addToCart(
                userDetails.getId(), request.getProductId(), request.getQuantity()));
    }

    @PutMapping("/update")
    public ResponseEntity<UserCart> updateCartItem(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody CartItemRequest request) {
        return ResponseEntity.ok(cartService.updateCartItem(
                userDetails.getId(), request.getProductId(), request.getQuantity()));
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeFromCart(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long productId) {
        cartService.removeFromCart(userDetails.getId(), productId);
        return ResponseEntity.ok("Item removed from cart");
    }

    @DeleteMapping("/clear")
    public ResponseEntity<?> clearCart(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        cartService.clearCart(userDetails.getId());
        return ResponseEntity.ok("Cart cleared");
    }
}
