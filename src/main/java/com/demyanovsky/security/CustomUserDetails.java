package com.demyanovsky.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {

    private com.demyanovsky.domain.User user;
    private Object User;

    public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public com.demyanovsky.domain.User getUser() {
        return user;
    }
}