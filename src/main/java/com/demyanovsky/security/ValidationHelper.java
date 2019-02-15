package com.demyanovsky.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.xml.bind.ValidationException;
import java.util.Collection;
import java.util.UUID;

public final class ValidationHelper {
    private ValidationHelper() {
    }

    public static String getRole() throws ValidationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            Collection<GrantedAuthority> grantedAuthority = ((CustomUserDetails) principal).getAuthorities();
            return grantedAuthority.stream().findFirst().get().toString();
        } else {
            throw new ValidationException(principal.toString());
        }
    }

    public static UUID getId() throws ValidationException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            return ((CustomUserDetails) principal).getId();
        } else {
            throw new ValidationException(principal.toString());
        }
    }
}
