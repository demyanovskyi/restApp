package com.demyanovsky.security;

import com.demyanovsky.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PasswordEncoder ew = getPasswordEncoder();
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        String salt = userRepository.findByName(name).getSalt();
        UserDetails userDetails = null;
        try {
            userDetails = getUserDetailsService().loadUserByUsername(name);
        } catch (UsernameNotFoundException ex) {
        }
        if (userDetails != null) {
            if (bCryptPasswordEncoder.matches(password + salt, userDetails.getPassword())) {
                return new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
            }
        }
        throw new BadCredentialsException(messages.getMessage("CustomDaoAuthenticationProvider.badCredentials", "Bad credentials"));
    }
}
