package com.deltech.backend.repository;

import com.deltech.backend.entity.AdminRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRoleRepository extends JpaRepository<AdminRole, Long> {}
