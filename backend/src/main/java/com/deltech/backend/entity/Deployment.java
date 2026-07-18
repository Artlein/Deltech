package com.deltech.backend.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "deployments")
public class Deployment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(nullable = false, length = 255)
    private String name;

    @Column(nullable = false, length = 255)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deployment_leader_id", nullable = false)
    private Admin deploymentLeader;

    @Column(name = "creation_time", nullable = false, updatable = false)
    private LocalDateTime creationTime = LocalDateTime.now();

    @Column(nullable = false, length = 20)
    private String status = "pending";

    public Deployment() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Order order; private String name; private String location;
        private Admin deploymentLeader; private String status = "pending";
        public Builder order(Order v) { this.order = v; return this; }
        public Builder name(String v) { this.name = v; return this; }
        public Builder location(String v) { this.location = v; return this; }
        public Builder deploymentLeader(Admin v) { this.deploymentLeader = v; return this; }
        public Builder status(String v) { this.status = v; return this; }
        public Deployment build() {
            Deployment e = new Deployment();
            e.order = order; e.name = name; e.location = location;
            e.deploymentLeader = deploymentLeader; e.status = status;
            return e;
        }
    }

    public Long getId() { return id; }
    public Order getOrder() { return order; }
    public void setOrder(Order v) { this.order = v; }
    public String getName() { return name; }
    public void setName(String v) { this.name = v; }
    public String getLocation() { return location; }
    public void setLocation(String v) { this.location = v; }
    public Admin getDeploymentLeader() { return deploymentLeader; }
    public void setDeploymentLeader(Admin v) { this.deploymentLeader = v; }
    public LocalDateTime getCreationTime() { return creationTime; }
    public String getStatus() { return status; }
    public void setStatus(String v) { this.status = v; }
}
