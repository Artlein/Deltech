package com.deltech.backend.service;

import com.deltech.backend.dto.ContactRequest;
import com.deltech.backend.entity.Contact;
import com.deltech.backend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public Contact saveContact(ContactRequest request) {
        Contact contact = Contact.builder()
                .name(request.getName())
                .email(request.getEmail())
                .subject(request.getSubject())
                .message(request.getMessage())
                .build();
        return contactRepository.save(contact);
    }

    public List<Contact> getAllInquiries() {
        return contactRepository.findAll();
    }
}
