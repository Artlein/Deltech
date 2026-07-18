package com.deltech.backend.repository;

import com.deltech.backend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByUsername(String username);
    Optional<Customer> findByEmailCustomer(String email);
    Optional<Customer> findByResetToken(String resetToken);
    boolean existsByUsername(String username);
    boolean existsByEmailCustomer(String email);
}
