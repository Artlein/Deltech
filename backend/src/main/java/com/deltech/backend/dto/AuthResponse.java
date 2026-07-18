package com.deltech.backend.dto;

public class AuthResponse {
    private String token;
    private Long id;
    private String username;
    private String fullname;
    private String role;
    private Boolean is2faRequired;
    private String message;

    public AuthResponse() {}

    public AuthResponse(String token, Long id, String username, String fullname, String role, Boolean is2faRequired, String message) {
        this.token = token;
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.role = role;
        this.is2faRequired = is2faRequired;
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String token;
        private Long id;
        private String username;
        private String fullname;
        private String role;
        private Boolean is2faRequired;
        private String message;

        public Builder token(String token) { this.token = token; return this; }
        public Builder id(Long id) { this.id = id; return this; }
        public Builder username(String username) { this.username = username; return this; }
        public Builder fullname(String fullname) { this.fullname = fullname; return this; }
        public Builder role(String role) { this.role = role; return this; }
        public Builder is2faRequired(Boolean is2faRequired) { this.is2faRequired = is2faRequired; return this; }
        public Builder message(String message) { this.message = message; return this; }

        public AuthResponse build() {
            return new AuthResponse(token, id, username, fullname, role, is2faRequired, message);
        }
    }

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
    public String getFullname() { return fullname; }
    public void setFullname(String fullname) { this.fullname = fullname; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public Boolean getIs2faRequired() { return is2faRequired; }
    public void setIs2faRequired(Boolean is2faRequired) { this.is2faRequired = is2faRequired; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}
