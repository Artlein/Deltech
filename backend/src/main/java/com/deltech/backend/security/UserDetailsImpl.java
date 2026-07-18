package com.deltech.backend.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private final Long id;
    private final String username;
    private final String password;
    private final String email;
    private final String fullname;
    private final String role;
    private final boolean isVerified;
    private final boolean isAdmin;

    public UserDetailsImpl(Long id, String username, String password, String email, String fullname, String role, boolean isVerified, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullname = fullname;
        this.role = role;
        this.isVerified = isVerified;
        this.isAdmin = isAdmin;
    }

    public Long getId() { return id; }
    public String getEmail() { return email; }
    public String getFullname() { return fullname; }
    public String getRole() { return role; }
    public boolean isVerified() { return isVerified; }
    public boolean isAdmin() { return isAdmin; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isVerified;
    }
}
