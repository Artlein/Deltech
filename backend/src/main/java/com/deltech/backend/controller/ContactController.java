package com.deltech.backend.controller;

import com.deltech.backend.dto.ContactRequest;
import com.deltech.backend.entity.Contact;
import com.deltech.backend.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping
    public ResponseEntity<Contact> submitContact(@RequestBody ContactRequest request) {
        return ResponseEntity.ok(contactService.saveContact(request));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('SUPER_ADMIN', 'ADMIN_LEAD', 'STANDARD_ADMIN')")
    public ResponseEntity<List<Contact>> getInquiries() {
        return ResponseEntity.ok(contactService.getAllInquiries());
    }
}
