package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "support_tickets")
public class SupportTicket {

    @Id
    @Column(name = "ticket_id", length = 20)
    private String ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false, length = 5)
    private String resolved = "no";

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public SupportTicket() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private String ticketId; private Customer customer; private Product product;
        private String resolved = "no";
        public Builder ticketId(String v) { this.ticketId = v; return this; }
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder product(Product v) { this.product = v; return this; }
        public Builder resolved(String v) { this.resolved = v; return this; }
        public SupportTicket build() {
            SupportTicket e = new SupportTicket();
            e.ticketId = ticketId; e.customer = customer; e.product = product; e.resolved = resolved;
            return e;
        }
    }

    public String getTicketId() { return ticketId; }
    public void setTicketId(String v) { this.ticketId = v; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer v) { this.customer = v; }
    public Product getProduct() { return product; }
    public void setProduct(Product v) { this.product = v; }
    public String getResolved() { return resolved; }
    public void setResolved(String v) { this.resolved = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
