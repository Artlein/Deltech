package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customer_2fa")
public class Customer2fa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "otp_secret", nullable = false, length = 255)
    private String otpSecret;

    @Column(name = "is_2fa_enabled", nullable = false)
    private Boolean is2faEnabled = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PreUpdate
    public void preUpdate() { this.updatedAt = LocalDateTime.now(); }

    public Customer2fa() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Customer customer; private String otpSecret; private Boolean is2faEnabled = false;
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder otpSecret(String v) { this.otpSecret = v; return this; }
        public Builder is2faEnabled(Boolean v) { this.is2faEnabled = v; return this; }
        public Customer2fa build() {
            Customer2fa e = new Customer2fa();
            e.customer = customer; e.otpSecret = otpSecret; e.is2faEnabled = is2faEnabled;
            return e;
        }
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer v) { this.customer = v; }
    public String getOtpSecret() { return otpSecret; }
    public void setOtpSecret(String v) { this.otpSecret = v; }
    public Boolean getIs2faEnabled() { return is2faEnabled; }
    public void setIs2faEnabled(Boolean v) { this.is2faEnabled = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
