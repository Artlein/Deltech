package com.deltech.backend.dto;

public class RegisterRequest {
    private String name, username, email, phone, address, password;
    private String companyName, companyAddress, jobTitle;
    public RegisterRequest() {}
    public String getName() { return name; } public void setName(String v) { this.name = v; }
    public String getUsername() { return username; } public void setUsername(String v) { this.username = v; }
    public String getEmail() { return email; } public void setEmail(String v) { this.email = v; }
    public String getPhone() { return phone; } public void setPhone(String v) { this.phone = v; }
    public String getAddress() { return address; } public void setAddress(String v) { this.address = v; }
    public String getPassword() { return password; } public void setPassword(String v) { this.password = v; }
    public String getCompanyName() { return companyName; } public void setCompanyName(String v) { this.companyName = v; }
    public String getCompanyAddress() { return companyAddress; } public void setCompanyAddress(String v) { this.companyAddress = v; }
    public String getJobTitle() { return jobTitle; } public void setJobTitle(String v) { this.jobTitle = v; }
}
