package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deployment_logs")
public class DeploymentLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deployment_id", nullable = false)
    private Deployment deployment;

    @Column(nullable = false, length = 20)
    private String status = "completed";

    @Column(name = "deployment_date", nullable = false)
    private LocalDateTime deploymentDate = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "completed_at", nullable = false)
    private LocalDateTime completedAt = LocalDateTime.now();

    @Column(name = "deployment_name", nullable = false, length = 255)
    private String deploymentName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deployment_leader_id", nullable = false)
    private Admin deploymentLeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    public DeploymentLog() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Deployment deployment; private String status = "completed";
        private Order order; private Customer customer;
        private LocalDateTime completedAt = LocalDateTime.now();
        private String deploymentName; private Admin deploymentLeader; private Admin admin;
        public Builder deployment(Deployment v) { this.deployment = v; return this; }
        public Builder status(String v) { this.status = v; return this; }
        public Builder order(Order v) { this.order = v; return this; }
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder completedAt(LocalDateTime v) { this.completedAt = v; return this; }
        public Builder deploymentName(String v) { this.deploymentName = v; return this; }
        public Builder deploymentLeader(Admin v) { this.deploymentLeader = v; return this; }
        public Builder admin(Admin v) { this.admin = v; return this; }
        public DeploymentLog build() {
            DeploymentLog e = new DeploymentLog();
            e.deployment = deployment; e.status = status; e.order = order;
            e.customer = customer; e.completedAt = completedAt; e.deploymentName = deploymentName;
            e.deploymentLeader = deploymentLeader; e.admin = admin;
            return e;
        }
    }

    public Long getId() { return id; }
    public Deployment getDeployment() { return deployment; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
    public LocalDateTime getDeploymentDate() { return deploymentDate; }
    public Order getOrder() { return order; }
    public Customer getCustomer() { return customer; }
    public LocalDateTime getCompletedAt() { return completedAt; }
    public String getDeploymentName() { return deploymentName; }
    public Admin getDeploymentLeader() { return deploymentLeader; }
    public Admin getAdmin() { return admin; }
}
