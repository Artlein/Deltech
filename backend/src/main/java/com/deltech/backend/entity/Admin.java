package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 20)
    private String username;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(nullable = false, length = 50)
    private String fullname;

    @Column(nullable = false, unique = true, length = 255)
    private String email;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 20)
    private String status = "active";

    @Column(name = "create_token", length = 6)
    private String createToken = "";

    @Column(name = "reset_token", length = 255)
    private String resetToken;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private AdminRole role;

    public Admin() {}

    public Admin(Long id, String username, String password, String fullname, String email, String phone, String status, String createToken, String resetToken, AdminRole role) {
        this.id = id; this.username = username; this.password = password; this.fullname = fullname;
        this.email = email; this.phone = phone; this.status = status; this.createToken = createToken;
        this.resetToken = resetToken; this.role = role;
    }

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id; private String username; private String password; private String fullname;
        private String email; private String phone; private String status = "active";
        private String createToken = ""; private String resetToken; private AdminRole role;
        public Builder id(Long id) { this.id = id; return this; }
        public Builder username(String v) { this.username = v; return this; }
        public Builder password(String v) { this.password = v; return this; }
        public Builder fullname(String v) { this.fullname = v; return this; }
        public Builder email(String v) { this.email = v; return this; }
        public Builder phone(String v) { this.phone = v; return this; }
        public Builder status(String v) { this.status = v; return this; }
        public Builder createToken(String v) { this.createToken = v; return this; }
        public Builder resetToken(String v) { this.resetToken = v; return this; }
        public Builder role(AdminRole v) { this.role = v; return this; }
        public Admin build() { return new Admin(id, username, password, fullname, email, phone, status, createToken, resetToken, role); }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getCreateToken() { return createToken; }
    public void setCreateToken(String createToken) { this.createToken = createToken; }
    public String getResetToken() { return resetToken; }
    public void setResetToken(String resetToken) { this.resetToken = resetToken; }
    public AdminRole getRole() { return role; }
    public void setRole(AdminRole role) { this.role = role; }
}
