package com.deltech.backend.repository;

import com.deltech.backend.entity.Customer2fa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface Customer2faRepository extends JpaRepository<Customer2fa, Long> {
    Optional<Customer2fa> findByCustomerId(Long customerId);
}
