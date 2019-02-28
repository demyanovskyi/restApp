package com.demyanovsky.security;

import com.demyanovsky.exceptions.IncorrectSecurityContentException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.UUID;

public final class AccessControlHelper {
    private AccessControlHelper() {
    }

    public static String getRole() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails) {
            Collection<GrantedAuthority> grantedAuthority = ((CustomUserDetails) principal).getAuthorities();
          GrantedAuthority gA =  grantedAuthority.stream().findFirst().orElse(null);
            if (gA.equals(null)){
                throw new IncorrectSecurityContentException();
            }
            return gA.toString();
        } else {
            throw new IncorrectSecurityContentException();
        }
    }


    public static UUID getId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof CustomUserDetails && ((CustomUserDetails) principal).getId() != null) {
            return ((CustomUserDetails) principal).getId();
        } else {
            throw new IncorrectSecurityContentException();
        }
    }
}
