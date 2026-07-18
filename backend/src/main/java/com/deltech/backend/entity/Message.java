package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "ticket_id", nullable = false, length = 20)
    private String ticketId;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(name = "sender_type", nullable = false, length = 10)
    private String senderType;

    @Column(name = "sender_id", nullable = false)
    private Long senderId = 0L;

    @Column(nullable = false, updatable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public Message() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Customer customer; private Admin admin; private Product product;
        private String ticketId; private String message; private String senderType;
        private Long senderId = 0L;
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder admin(Admin v) { this.admin = v; return this; }
        public Builder product(Product v) { this.product = v; return this; }
        public Builder ticketId(String v) { this.ticketId = v; return this; }
        public Builder message(String v) { this.message = v; return this; }
        public Builder senderType(String v) { this.senderType = v; return this; }
        public Builder senderId(Long v) { this.senderId = v; return this; }
        public Message build() {
            Message e = new Message();
            e.customer = customer; e.admin = admin; e.product = product;
            e.ticketId = ticketId; e.message = message; e.senderType = senderType;
            e.senderId = senderId;
            return e;
        }
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer v) { this.customer = v; }
    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin v) { this.admin = v; }
    public Product getProduct() { return product; }
    public void setProduct(Product v) { this.product = v; }
    public String getTicketId() { return ticketId; }
    public void setTicketId(String v) { this.ticketId = v; }
    public String getMessage() { return message; }
    public void setMessage(String v) { this.message = v; }
    public String getSenderType() { return senderType; }
    public void setSenderType(String v) { this.senderType = v; }
    public Long getSenderId() { return senderId; }
    public void setSenderId(Long v) { this.senderId = v; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime v) { this.timestamp = v; }
}
