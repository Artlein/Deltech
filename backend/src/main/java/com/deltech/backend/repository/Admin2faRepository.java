package com.deltech.backend.repository;

import com.deltech.backend.entity.Admin2fa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Admin2faRepository extends JpaRepository<Admin2fa, Long> {
    Optional<Admin2fa> findByAdminId(Long adminId);
}
