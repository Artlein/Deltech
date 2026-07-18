package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin_2fa")
public class Admin2fa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

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

    public Admin2fa() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id; private Admin admin; private String otpSecret;
        private Boolean is2faEnabled = false;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
        public Builder id(Long v) { this.id = v; return this; }
        public Builder admin(Admin v) { this.admin = v; return this; }
        public Builder otpSecret(String v) { this.otpSecret = v; return this; }
        public Builder is2faEnabled(Boolean v) { this.is2faEnabled = v; return this; }
        public Admin2fa build() {
            Admin2fa e = new Admin2fa();
            e.id = id; e.admin = admin; e.otpSecret = otpSecret;
            e.is2faEnabled = is2faEnabled; e.createdAt = createdAt; e.updatedAt = updatedAt;
            return e;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin admin) { this.admin = admin; }
    public String getOtpSecret() { return otpSecret; }
    public void setOtpSecret(String otpSecret) { this.otpSecret = otpSecret; }
    public Boolean getIs2faEnabled() { return is2faEnabled; }
    public void setIs2faEnabled(Boolean v) { this.is2faEnabled = v; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime v) { this.createdAt = v; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime v) { this.updatedAt = v; }
}
