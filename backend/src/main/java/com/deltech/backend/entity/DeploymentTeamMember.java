package com.deltech.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "deployment_team_members",
       uniqueConstraints = @UniqueConstraint(columnNames = {"deployment_id", "admin_id"}))
public class DeploymentTeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deployment_id", nullable = false)
    private Deployment deployment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    public DeploymentTeamMember() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Deployment deployment; private Admin admin;
        public Builder deployment(Deployment v) { this.deployment = v; return this; }
        public Builder admin(Admin v) { this.admin = v; return this; }
        public DeploymentTeamMember build() {
            DeploymentTeamMember e = new DeploymentTeamMember();
            e.deployment = deployment; e.admin = admin;
            return e;
        }
    }

    public Long getId() { return id; }
    public Deployment getDeployment() { return deployment; }
    public void setDeployment(Deployment v) { this.deployment = v; }
    public Admin getAdmin() { return admin; }
    public void setAdmin(Admin v) { this.admin = v; }
}
