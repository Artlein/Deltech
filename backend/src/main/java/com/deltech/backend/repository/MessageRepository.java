package com.deltech.backend.repository;

import com.deltech.backend.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByTicketIdOrderByTimestampAsc(String ticketId);
    List<Message> findBySenderTypeAndSenderId(String senderType, Long senderId);
}
