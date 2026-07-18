package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String subject = "none";

    @Column(nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "created_c", nullable = false, updatable = false)
    private LocalDateTime createdC = LocalDateTime.now();

    public Contact() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private String name; private String email; private String subject = "none"; private String message;
        public Builder name(String v) { this.name = v; return this; }
        public Builder email(String v) { this.email = v; return this; }
        public Builder subject(String v) { this.subject = v; return this; }
        public Builder message(String v) { this.message = v; return this; }
        public Contact build() {
            Contact e = new Contact();
            e.name = name; e.email = email; e.subject = subject; e.message = message;
            return e;
        }
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
    public String getEmail() { return email; }
    public void setEmail(String v) { this.email = v; }
    public String getSubject() { return subject; }
    public void setSubject(String v) { this.subject = v; }
    public String getMessage() { return message; }
    public void setMessage(String v) { this.message = v; }
    public LocalDateTime getCreatedC() { return createdC; }
}
