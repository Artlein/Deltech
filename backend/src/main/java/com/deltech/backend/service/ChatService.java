package com.deltech.backend.service;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.Message;
import com.deltech.backend.entity.Product;
import com.deltech.backend.entity.SupportTicket;
import com.deltech.backend.repository.CustomerRepository;
import com.deltech.backend.repository.MessageRepository;
import com.deltech.backend.repository.ProductRepository;
import com.deltech.backend.repository.SupportTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ChatService {

    @Autowired
    private SupportTicketRepository ticketRepository;

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<SupportTicket> getCustomerTickets(Long customerId) {
        return ticketRepository.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    public List<SupportTicket> getAllTickets() {
        return ticketRepository.findAllByOrderByCreatedAtDesc();
    }

    public SupportTicket createTicket(Long customerId, Long productId, String initialMessage) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        String ticketId = "TICKET-" + System.currentTimeMillis();

        SupportTicket ticket = SupportTicket.builder()
                .ticketId(ticketId)
                .customer(customer)
                .product(product)
                .resolved("no")
                .build();
        ticketRepository.save(ticket);

        // Save initial message
        if (initialMessage != null && !initialMessage.isEmpty()) {
            Message message = Message.builder()
                    .customer(customer)
                    .product(product)
                    .ticketId(ticketId)
                    .message(initialMessage)
                    .senderType("customer")
                    .senderId(customerId)
                    .build();
            messageRepository.save(message);
        }

        return ticket;
    }

    public void resolveTicket(String ticketId, boolean resolved) {
        SupportTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));
        ticket.setResolved(resolved ? "yes" : "no");
        ticketRepository.save(ticket);
    }

    public List<Message> getTicketMessages(String ticketId) {
        return messageRepository.findByTicketIdOrderByTimestampAsc(ticketId);
    }

    public Message sendCustomerMessage(Long customerId, String ticketId, String messageContent) {
        SupportTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Message message = Message.builder()
                .customer(ticket.getCustomer())
                .product(ticket.getProduct())
                .ticketId(ticketId)
                .message(messageContent)
                .senderType("customer")
                .senderId(customerId)
                .build();

        return messageRepository.save(message);
    }

    public Message sendAdminMessage(Long adminId, String ticketId, String messageContent) {
        SupportTicket ticket = ticketRepository.findById(ticketId)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        Message message = Message.builder()
                .customer(ticket.getCustomer())
                .product(ticket.getProduct())
                .ticketId(ticketId)
                .message(messageContent)
                .senderType("admin")
                .senderId(adminId)
                .build();

        return messageRepository.save(message);
    }
}
