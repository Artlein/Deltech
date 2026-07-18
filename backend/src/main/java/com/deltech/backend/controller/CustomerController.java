package com.deltech.backend.controller;

import com.deltech.backend.entity.Customer;
import com.deltech.backend.entity.CustomerCompany;
import com.deltech.backend.repository.CustomerCompanyRepository;
import com.deltech.backend.repository.CustomerRepository;
import com.deltech.backend.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerCompanyRepository companyRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Optional<Customer> customerOpt = customerRepository.findById(userDetails.getId());
        if (customerOpt.isPresent()) {
            return ResponseEntity.ok(customerOpt.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload-document")
    public ResponseEntity<?> uploadDocument(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Create upload dir if not exists
            File directory = new File(uploadDir + "/customerfileupload");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String uniqueName = UUID.randomUUID().toString() + ext;
            Path path = Paths.get(directory.getAbsolutePath() + File.separator + uniqueName);
            Files.write(path, file.getBytes());

            Optional<CustomerCompany> companyOpt = companyRepository.findByCustomerId(userDetails.getId());
            CustomerCompany company;
            if (companyOpt.isPresent()) {
                company = companyOpt.get();
                company.setBusinessDocument("uploads/customerfileupload/" + uniqueName);
            } else {
                Customer customer = customerRepository.findById(userDetails.getId())
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
                company = CustomerCompany.builder()
                        .customer(customer)
                        .companyName("Default Company")
                        .companyAddress(customer.getAddress())
                        .jobTitle("Guest")
                        .businessDocument("uploads/customerfileupload/" + uniqueName)
                        .build();
            }
            companyRepository.save(company);

            return ResponseEntity.ok("Document uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to store file: " + e.getMessage());
        }
    }

    @PostMapping("/upload-id")
    public ResponseEntity<?> uploadId(
            @AuthenticationPrincipal UserDetailsImpl userDetails,
            @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            File directory = new File(uploadDir + "/customeridupload");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
            String uniqueName = UUID.randomUUID().toString() + ext;
            Path path = Paths.get(directory.getAbsolutePath() + File.separator + uniqueName);
            Files.write(path, file.getBytes());

            Optional<CustomerCompany> companyOpt = companyRepository.findByCustomerId(userDetails.getId());
            CustomerCompany company;
            if (companyOpt.isPresent()) {
                company = companyOpt.get();
                company.setBusinessId("uploads/customeridupload/" + uniqueName);
            } else {
                Customer customer = customerRepository.findById(userDetails.getId())
                        .orElseThrow(() -> new RuntimeException("Customer not found"));
                company = CustomerCompany.builder()
                        .customer(customer)
                        .companyName("Default Company")
                        .companyAddress(customer.getAddress())
                        .jobTitle("Guest")
                        .businessId("uploads/customeridupload/" + uniqueName)
                        .build();
            }
            companyRepository.save(company);

            return ResponseEntity.ok("ID uploaded successfully");
        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to store file: " + e.getMessage());
        }
    }
}
