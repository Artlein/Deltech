package com.deltech.backend.repository;

import com.deltech.backend.entity.DeploymentTeamMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface DeploymentTeamMemberRepository extends JpaRepository<DeploymentTeamMember, Long> {
    List<DeploymentTeamMember> findByDeploymentId(Long deploymentId);
    void deleteByDeploymentId(Long deploymentId);
}
