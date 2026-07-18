package com.deltech.backend.service;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Product;
import com.deltech.backend.entity.UserCart;
import com.deltech.backend.repository.CustomerRepository;
import com.deltech.backend.repository.ProductRepository;
import com.deltech.backend.repository.UserCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CartService {

    @Autowired
    private UserCartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public List<UserCart> getCartItems(Long customerId) {
        return cartRepository.findByCustomerId(customerId);
    }

    public UserCart addToCart(Long customerId, Long productId, Integer quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<UserCart> existingOpt = cartRepository.findByCustomerIdAndProductId(customerId, productId);
        if (existingOpt.isPresent()) {
            UserCart cartItem = existingOpt.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            return cartRepository.save(cartItem);
        }

        UserCart cartItem = UserCart.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .productName(product.getNameProduct())
                .productPrice(product.getPriceProduct())
                .currency(product.getCurrency())
                .build();
        return cartRepository.save(cartItem);
    }

    public UserCart updateCartItem(Long customerId, Long productId, Integer quantity) {
        UserCart cartItem = cartRepository.findByCustomerIdAndProductId(customerId, productId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));
        cartItem.setQuantity(quantity);
        return cartRepository.save(cartItem);
    }

    public void removeFromCart(Long customerId, Long productId) {
        cartRepository.deleteByCustomerIdAndProductId(customerId, productId);
    }

    public void clearCart(Long customerId) {
        cartRepository.deleteByCustomerId(customerId);
    }
}
