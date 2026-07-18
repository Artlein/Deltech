package com.deltech.backend.repository;

import com.deltech.backend.entity.SupportTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SupportTicketRepository extends JpaRepository<SupportTicket, String> {
    List<SupportTicket> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
    List<SupportTicket> findAllByOrderByCreatedAtDesc();
    long countByResolved(String resolved);
}
