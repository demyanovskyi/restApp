package com.demyanovsky.security;

import com.demyanovsky.exceptions.IncorrectSecurityContentException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.Objects;
import java.util.UUID;

public final class AccessControlHelper {
    private AccessControlHelper() {
    }

    public static String getRole() throws IncorrectSecurityContentException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            Collection<GrantedAuthority> grantedAuthority = ((CustomUserDetails) principal).getAuthorities();
            return grantedAuthority.stream().findFirst().map(Objects::toString).orElseThrow(() -> new IncorrectSecurityContentException());
        }
        throw new IllegalArgumentException();
    }

    public static UUID getId() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails && ((CustomUserDetails) principal).getId() != null) {
            return ((CustomUserDetails) principal).getId();
        } else {
            throw new IncorrectSecurityContentException();
        }
    }
}
