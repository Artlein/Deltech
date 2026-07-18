package com.deltech.backend.dto;

import java.util.List;

public class DeploymentRequest {
    private Long orderId;
    private String name;
    private String location;
    private Long deploymentLeaderId;
    private List<Long> teamMemberIds;

    public DeploymentRequest() {}

    public Long getOrderId() { return orderId; }
    public void setOrderId(Long orderId) { this.orderId = orderId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }
    public Long getDeploymentLeaderId() { return deploymentLeaderId; }
    public void setDeploymentLeaderId(Long deploymentLeaderId) { this.deploymentLeaderId = deploymentLeaderId; }
    public List<Long> getTeamMemberIds() { return teamMemberIds; }
    public void setTeamMemberIds(List<Long> teamMemberIds) { this.teamMemberIds = teamMemberIds; }
}
