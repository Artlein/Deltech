package com.deltech.backend.service;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Order;
import com.deltech.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AdminService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private SupportTicketRepository ticketRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private ProductRepository productRepository;

    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        Double totalSales = orderRepository.sumCompletedSubtotals();
        stats.put("totalSales", totalSales != null ? totalSales : 0.0);

        stats.put("totalCustomers", customerRepository.count());
        stats.put("totalTickets", ticketRepository.count());
        
        long totalInquiries = ticketRepository.countByResolved("no"); // Approximate tickets/chats
        stats.put("totalInquiries", totalInquiries);

        
        return stats;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void verifyCustomer(Long customerId, boolean isVerified) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setIsVerified(isVerified);
        customerRepository.save(customer);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setOrderStatus(status);
        orderRepository.save(order);
    }
}
