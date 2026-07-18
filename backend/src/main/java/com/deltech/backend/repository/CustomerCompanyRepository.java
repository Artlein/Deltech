package com.deltech.backend.repository;

import com.deltech.backend.entity.CustomerCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerCompanyRepository extends JpaRepository<CustomerCompany, Long> {
    Optional<CustomerCompany> findByCustomerId(Long customerId);
}
