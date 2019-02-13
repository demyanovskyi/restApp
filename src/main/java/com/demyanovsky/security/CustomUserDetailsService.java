package com.demyanovsky.security;


import com.demyanovsky.domain.User;
import com.demyanovsky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;

@Service()
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public CustomUserDetailsService() {
        super();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username);
        if (user != null) return new CustomUserDetails(user.getName(), user.getPassword(), getAuthorities(username));
        return null;
    }

    private Collection<GrantedAuthority> getAuthorities(String username) {
        Collection<GrantedAuthority> authorities = new HashSet<>();
        User user = userRepository.findByName(username);
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(user.getRole().toString());
        authorities.add(grantedAuthority);
        return authorities;
    }
}
