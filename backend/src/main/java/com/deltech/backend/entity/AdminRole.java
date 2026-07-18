package com.deltech.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "admin_role")
public class AdminRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name", nullable = false, length = 100)
    private String roleName;

    public AdminRole() {}
    public AdminRole(Long id, String roleName) { this.id = id; this.roleName = roleName; }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getRoleName() { return roleName; }
    public void setRoleName(String roleName) { this.roleName = roleName; }
}
