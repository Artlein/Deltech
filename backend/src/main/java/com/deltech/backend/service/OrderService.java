package com.deltech.backend.service;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Order;
import com.deltech.backend.entity.UserCart;
import com.deltech.backend.repository.CustomerRepository;
import com.deltech.backend.repository.OrderRepository;
import com.deltech.backend.repository.UserCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserCartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;

    public List<Order> getCustomerOrders(Long customerId) {
        return orderRepository.findByCustomerIdOrderByOrderDateDesc(customerId);
    }

    public Order checkout(Long customerId, String noteCustomer) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        List<UserCart> cartItems = cartRepository.findByCustomerId(customerId);
        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        // Create an order number like #12345
        String orderNumber = "#" + UUID.randomUUID().toString().substring(0, 5).toUpperCase();

        // Concatenate product info for order tables if needed, or create single order record
        // The legacy PHP db structure stores items as separate rows or concatenated text.
        // Looking at the sql schema:
        // CREATE TABLE `orders` (
        //   `id` int(11) NOT NULL,
        //   `orders_number` varchar(20) NOT NULL,
        //   `customer_id` int(11) NOT NULL,
        //   `product_name` text NOT NULL,
        //   `product_quantity` varchar(20) NOT NULL,
        //   `product_price` decimal(10,2) NOT NULL,
        //   `currency` varchar(20) NOT NULL,
        //   `subtotal` varchar(20) NOT NULL,
        //   `note_customer` text NOT NULL,
        //   `order_date` timestamp NOT NULL DEFAULT current_timestamp(),
        //   `order_status` enum(...) NOT NULL DEFAULT 'pending payment'
        // )
        //
        // This means each cart item becomes a separate Order record but shares the same order_number.
        // Let's implement that legacy mapping.
        List<Order> savedOrders = new ArrayList<>();
        BigDecimal totalSubtotal = BigDecimal.ZERO;
        StringBuilder emailBody = new StringBuilder("Thank you for your order! Here are the details of your reservation:\n\n");
        emailBody.append("Order Number: ").append(orderNumber).append("\n\n");

        for (UserCart item : cartItems) {
            BigDecimal subtotalVal = item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
            totalSubtotal = totalSubtotal.add(subtotalVal);

            Order order = Order.builder()
                    .ordersNumber(orderNumber)
                    .customer(customer)
                    .productName(item.getProductName())
                    .productQuantity(String.valueOf(item.getQuantity()))
                    .productPrice(item.getProductPrice())
                    .currency(item.getCurrency())
                    .subtotal(subtotalVal.toString())
                    .noteCustomer(noteCustomer != null ? noteCustomer : customer.getNameCustomer())
                    .orderStatus("pending payment")
                    .build();

            savedOrders.add(orderRepository.save(order));

            emailBody.append("- ").append(item.getProductName())
                    .append(" x").append(item.getQuantity())
                    .append(" (").append(item.getCurrency()).append(" ").append(subtotalVal).append(")\n");
        }

        emailBody.append("\nTotal: ").append(cartItems.get(0).getCurrency()).append(" ").append(totalSubtotal).append("\n\n");
        emailBody.append("Our team will review your payment. Please follow payment guidelines.");

        // Clear user cart
        cartRepository.deleteByCustomerId(customerId);

        // Send email confirmation
        emailService.sendEmail(customer.getEmailCustomer(), "Order Confirmation " + orderNumber, emailBody.toString());

        return savedOrders.isEmpty() ? null : savedOrders.get(0);
    }
}
