package com.deltech.backend.controller;

import com.deltech.backend.dto.MessageRequest;
import com.deltech.backend.dto.TicketRequest;
import com.deltech.backend.entity.Message;
import com.deltech.backend.entity.SupportTicket;
import com.deltech.backend.security.UserDetailsImpl;
import com.deltech.backend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tickets")
public class ChatController {

    @Autowired
    private ChatService chatService;

    @GetMapping("/my")
    public ResponseEntity<List<SupportTicket>> getMyTickets(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(chatService.getCustomerTickets(userDetails.getId()));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD', 'STANDARD_ADMIN')")
    public ResponseEntity<List<SupportTicket>> getAllTickets() {
        return ResponseEntity.ok(chatService.getAllTickets());
    }

    @PostMapping
    public ResponseEntity<SupportTicket> createTicket(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestBody TicketRequest request) {
        return ResponseEntity.ok(chatService.createTicket(
                userDetails.getId(), request.getProductId(), request.getInitialMessage()));
    }

    @PutMapping("/{ticketId}/resolve")
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD', 'STANDARD_ADMIN')")
    public ResponseEntity<?> resolveTicket(
            @PathVariable String ticketId,
            @RequestParam boolean resolved) {
        try {
            chatService.resolveTicket(ticketId, resolved);
            return ResponseEntity.ok("Ticket resolved status updated");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{ticketId}/messages")
    public ResponseEntity<List<Message>> getMessages(@PathVariable String ticketId) {
        return ResponseEntity.ok(chatService.getTicketMessages(ticketId));
    }

    @PostMapping("/{ticketId}/messages")
    public ResponseEntity<Message> sendMessage(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @PathVariable String ticketId,
            @RequestBody MessageRequest request) {
        if (userDetails.isAdmin()) {
            return ResponseEntity.ok(chatService.sendAdminMessage(
                    userDetails.getId(), ticketId, request.getMessage()));
        } else {
            return ResponseEntity.ok(chatService.sendCustomerMessage(
                    userDetails.getId(), ticketId, request.getMessage()));
        }
    }
}
