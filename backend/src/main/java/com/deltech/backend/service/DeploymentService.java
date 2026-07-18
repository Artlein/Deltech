package com.deltech.backend.service;

import com.deltech.backend.dto.DeploymentRequest;
import com.deltech.backend.entity.*;
import com.deltech.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class DeploymentService {

    @Autowired
    private DeploymentRepository deploymentRepository;

    @Autowired
    private DeploymentLogRepository logRepository;

    @Autowired
    private DeploymentTeamMemberRepository teamMemberRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AdminRepository adminRepository;

    public List<Deployment> getAllDeployments() {
        return deploymentRepository.findAll();
    }

    public Deployment createDeployment(DeploymentRequest request) {
        Order order = orderRepository.findById(request.getOrderId())
                .orElseThrow(() -> new RuntimeException("Order not found"));
        Admin leader = adminRepository.findById(request.getDeploymentLeaderId())
                .orElseThrow(() -> new RuntimeException("Leader admin not found"));

        Deployment deployment = Deployment.builder()
                .order(order)
                .name(request.getName())
                .location(request.getLocation())
                .deploymentLeader(leader)
                .status("pending")
                .build();
        deployment = deploymentRepository.save(deployment);

        if (request.getTeamMemberIds() != null) {
            for (Long memberId : request.getTeamMemberIds()) {
                Admin member = adminRepository.findById(memberId)
                        .orElseThrow(() -> new RuntimeException("Admin member not found"));
                DeploymentTeamMember teamMember = DeploymentTeamMember.builder()
                        .deployment(deployment)
                        .admin(member)
                        .build();
                teamMemberRepository.save(teamMember);
            }
        }

        return deployment;
    }

    public void updateDeploymentStatus(Long deploymentId, String status, Long adminId) {
        Deployment deployment = deploymentRepository.findById(deploymentId)
                .orElseThrow(() -> new RuntimeException("Deployment not found"));
        
        deployment.setStatus(status);
        deploymentRepository.save(deployment);

        if (status.equalsIgnoreCase("completed") || status.equalsIgnoreCase("cancelled")) {
            Admin loggingAdmin = adminRepository.findById(adminId)
                    .orElseThrow(() -> new RuntimeException("Admin not found"));

            DeploymentLog log = DeploymentLog.builder()
                    .deployment(deployment)
                    .status(status)
                    .order(deployment.getOrder())
                    .customer(deployment.getOrder().getCustomer())
                    .completedAt(LocalDateTime.now())
                    .deploymentName(deployment.getName())
                    .deploymentLeader(deployment.getDeploymentLeader())
                    .admin(loggingAdmin)
                    .build();
            logRepository.save(log);
        }
    }

    public List<DeploymentLog> getAllLogs() {
        return logRepository.findAllByOrderByDeploymentDateDesc();
    }
}
