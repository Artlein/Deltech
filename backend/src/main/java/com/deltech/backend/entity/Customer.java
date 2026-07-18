package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_customer", nullable = false, length = 50)
    private String nameCustomer;

    @Column(nullable = false, unique = true, length = 50)
    private String username;

    @Column(name = "email_customer", nullable = false, length = 255)
    private String emailCustomer;

    @Column(name = "phone_customer", nullable = false, length = 15)
    private String phoneCustomer;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    @Column(nullable = false, length = 255)
    private String password;

    @Column(name = "verification_code", length = 6)
    private String verificationCode;

    @Column(name = "is_verified", nullable = false)
    private Boolean isVerified = false;

    @Column(name = "note_customer", columnDefinition = "TEXT")
    private String noteCustomer;

    @Column(name = "reset_token", length = 255)
    private String resetToken;

    @Column(name = "date_at", nullable = false, updatable = false)
    private LocalDateTime dateAt = LocalDateTime.now();

    public Customer() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Long id; private String nameCustomer; private String username;
        private String emailCustomer; private String phoneCustomer; private String address;
        private String password; private String verificationCode; private Boolean isVerified = false;
        private String noteCustomer; private String resetToken;
        private LocalDateTime dateAt = LocalDateTime.now();
        public Builder id(Long v) { this.id = v; return this; }
        public Builder nameCustomer(String v) { this.nameCustomer = v; return this; }
        public Builder username(String v) { this.username = v; return this; }
        public Builder emailCustomer(String v) { this.emailCustomer = v; return this; }
        public Builder phoneCustomer(String v) { this.phoneCustomer = v; return this; }
        public Builder address(String v) { this.address = v; return this; }
        public Builder password(String v) { this.password = v; return this; }
        public Builder verificationCode(String v) { this.verificationCode = v; return this; }
        public Builder isVerified(Boolean v) { this.isVerified = v; return this; }
        public Builder noteCustomer(String v) { this.noteCustomer = v; return this; }
        public Builder resetToken(String v) { this.resetToken = v; return this; }
        public Customer build() {
            Customer e = new Customer();
            e.id = id; e.nameCustomer = nameCustomer; e.username = username;
            e.emailCustomer = emailCustomer; e.phoneCustomer = phoneCustomer; e.address = address;
            e.password = password; e.verificationCode = verificationCode; e.isVerified = isVerified;
            e.noteCustomer = noteCustomer; e.resetToken = resetToken; e.dateAt = dateAt;
            return e;
        }
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNameCustomer() { return nameCustomer; }
    public void setNameCustomer(String v) { this.nameCustomer = v; }
    public String getUsername() { return username; }
    public void setUsername(String v) { this.username = v; }
    public String getEmailCustomer() { return emailCustomer; }
    public void setEmailCustomer(String v) { this.emailCustomer = v; }
    public String getPhoneCustomer() { return phoneCustomer; }
    public void setPhoneCustomer(String v) { this.phoneCustomer = v; }
    public String getAddress() { return address; }
    public void setAddress(String v) { this.address = v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { this.password = v; }
    public String getVerificationCode() { return verificationCode; }
    public void setVerificationCode(String v) { this.verificationCode = v; }
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean v) { this.isVerified = v; }
    public String getNoteCustomer() { return noteCustomer; }
    public void setNoteCustomer(String v) { this.noteCustomer = v; }
    public String getResetToken() { return resetToken; }
    public void setResetToken(String v) { this.resetToken = v; }
    public LocalDateTime getDateAt() { return dateAt; }
    public void setDateAt(LocalDateTime v) { this.dateAt = v; }
}
