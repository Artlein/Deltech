package com.deltech.backend.controller;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Order;
import com.deltech.backend.repository.CustomerRepository;
import com.deltech.backend.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/downloads")
public class DownloadController {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping("/receipt/{orderNumber}")
    public ResponseEntity<byte[]> downloadReceipt(@PathVariable String orderNumber) {
        List<Order> orders = orderRepository.findByCustomerIdOrderByOrderDateDesc(1L); // Fetch mock or list
        Optional<Order> orderOpt = orderRepository.findByOrdersNumber(orderNumber);

        if (orderOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Order order = orderOpt.get();
        StringBuilder doc = new StringBuilder();
        doc.append("DELTECH PARKING SYSTEMS RECEIPT\n");
        doc.append("==================================\n");
        doc.append("Order Number: ").append(order.getOrdersNumber()).append("\n");
        doc.append("Customer: ").append(order.getCustomer().getNameCustomer()).append("\n");
        doc.append("Date: ").append(order.getOrderDate()).append("\n");
        doc.append("Product: ").append(order.getProductName()).append("\n");
        doc.append("Qty: ").append(order.getProductQuantity()).append("\n");
        doc.append("Price: ").append(order.getCurrency()).append(" ").append(order.getProductPrice()).append("\n");
        doc.append("Subtotal: ").append(order.getCurrency()).append(" ").append(order.getSubtotal()).append("\n");
        doc.append("==================================\n");
        doc.append("Thank you for reserving with us!");

        byte[] data = doc.toString().getBytes();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=receipt_" + orderNumber + ".txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(data);
    }

    @GetMapping("/sales")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD')")
    public ResponseEntity<byte[]> downloadSales() {
        List<Order> completed = orderRepository.findAll();
        StringBuilder doc = new StringBuilder();
        doc.append("DELTECH SALES REPORT\n");
        doc.append("==================================\n");
        for (Order o : completed) {
            doc.append(o.getOrdersNumber()).append(" | ")
                    .append(o.getProductName()).append(" | ")
                    .append(o.getCurrency()).append(" ")
                    .append(o.getSubtotal()).append("\n");
        }
        doc.append("==================================\n");

        byte[] data = doc.toString().getBytes();
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=sales_report.txt")
                .contentType(MediaType.TEXT_PLAIN)
                .body(data);
    }
}
