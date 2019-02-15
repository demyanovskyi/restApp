package com.demyanovsky.security;

import com.demyanovsky.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.security.NoSuchAlgorithmException;

public class CustomDaoAuthenticationProvider extends DaoAuthenticationProvider {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SHA256Generator sha256Generator;

    Logger logger = LoggerFactory.getLogger(CustomDaoAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        String salt = userRepository.findByName(name).getSalt();
        UserDetails u = null;
        try {
            u = getUserDetailsService().loadUserByUsername(name);
        } catch (UsernameNotFoundException ex) {
        }
        if (u != null) {
            try {
                if (u.getPassword().equals(sha256Generator.getHashPassword(password, salt))) {
                    return new UsernamePasswordAuthenticationToken(u, password, u.getAuthorities());
                }
            } catch (NoSuchAlgorithmException e) {
                logger.debug("No such algorithm available");
            }
        }
        throw new BadCredentialsException(messages.getMessage("CustomDaoAuthenticationProvider.badCredentials", "Bad credentials"));
    }

}
