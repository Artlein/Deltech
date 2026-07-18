package com.deltech.backend.repository;

import com.deltech.backend.entity.DeploymentLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeploymentLogRepository extends JpaRepository<DeploymentLog, Long> {
    List<DeploymentLog> findByDeploymentIdOrderByDeploymentDateDesc(Long deploymentId);
    List<DeploymentLog> findAllByOrderByDeploymentDateDesc();
}
