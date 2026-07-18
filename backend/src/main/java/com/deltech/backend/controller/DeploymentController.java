package com.deltech.backend.controller;

import com.deltech.backend.dto.DeploymentRequest;
import com.deltech.backend.entity.Deployment;
import com.deltech.backend.entity.DeploymentLog;
import com.deltech.backend.security.UserDetailsImpl;
import com.deltech.backend.service.DeploymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
@PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD', 'STANDARD_ADMIN')")
public class DeploymentController {

    @Autowired
    private DeploymentService deploymentService;

    @GetMapping
    public ResponseEntity<List<Deployment>> getAllDeployments() {
        return ResponseEntity.ok(deploymentService.getAllDeployments());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD')")
    public ResponseEntity<Deployment> createDeployment(@RequestBody DeploymentRequest request) {
        return ResponseEntity.ok(deploymentService.createDeployment(request));
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable Long id,
            @RequestParam String status) {
        try {
            deploymentService.updateDeploymentStatus(id, status, userDetails.getId());
            return ResponseEntity.ok("Deployment status updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/logs")
    public ResponseEntity<List<DeploymentLog>> getLogs() {
        return ResponseEntity.ok(deploymentService.getAllLogs());
    }
}
