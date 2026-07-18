package com.deltech.backend.dto;

public class LoginRequest {
    private String username;
    private String password;
    private String otp;
    public LoginRequest() {}
    public LoginRequest(String username, String password, String otp) { this.username = username; this.password = password; this.otp = otp; }
    public String getUsername() { return username; }
    public void setUsername(String v) { this.username = v; }
    public String getPassword() { return password; }
    public void setPassword(String v) { this.password = v; }
    public String getOtp() { return otp; }
    public void setOtp(String v) { this.otp = v; }
}
