package com.deltech.backend.repository;

import com.deltech.backend.entity.Deployment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeploymentRepository extends JpaRepository<Deployment, Long> {
    List<Deployment> findByOrderId(Long orderId);
    List<Deployment> findByStatus(String status);
}
