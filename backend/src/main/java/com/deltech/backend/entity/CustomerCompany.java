package com.deltech.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "customer_companies")
public class CustomerCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Column(name = "company_name", nullable = false, length = 255)
    private String companyName;

    @Column(name = "company_address", nullable = false, columnDefinition = "TEXT")
    private String companyAddress;

    @Column(name = "job_title", nullable = false, length = 100)
    private String jobTitle;

    @Column(name = "business_document", length = 255)
    private String businessDocument;

    @Column(name = "business_id", length = 255)
    private String businessId;

    public CustomerCompany() {}

    public static Builder builder() { return new Builder(); }
    public static class Builder {
        private Customer customer; private String companyName; private String companyAddress;
        private String jobTitle; private String businessDocument; private String businessId;
        public Builder customer(Customer v) { this.customer = v; return this; }
        public Builder companyName(String v) { this.companyName = v; return this; }
        public Builder companyAddress(String v) { this.companyAddress = v; return this; }
        public Builder jobTitle(String v) { this.jobTitle = v; return this; }
        public Builder businessDocument(String v) { this.businessDocument = v; return this; }
        public Builder businessId(String v) { this.businessId = v; return this; }
        public CustomerCompany build() {
            CustomerCompany e = new CustomerCompany();
            e.customer = customer; e.companyName = companyName; e.companyAddress = companyAddress;
            e.jobTitle = jobTitle; e.businessDocument = businessDocument; e.businessId = businessId;
            return e;
        }
    }

    public Long getId() { return id; }
    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer v) { this.customer = v; }
    public String getCompanyName() { return companyName; }
    public void setCompanyName(String v) { this.companyName = v; }
    public String getCompanyAddress() { return companyAddress; }
    public void setCompanyAddress(String v) { this.companyAddress = v; }
    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String v) { this.jobTitle = v; }
    public String getBusinessDocument() { return businessDocument; }
    public void setBusinessDocument(String v) { this.businessDocument = v; }
    public String getBusinessId() { return businessId; }
    public void setBusinessId(String v) { this.businessId = v; }
}
