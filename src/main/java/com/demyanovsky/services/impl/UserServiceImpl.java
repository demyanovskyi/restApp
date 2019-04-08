package com.demyanovsky.services.impl;


import com.demyanovsky.domain.*;
import com.demyanovsky.exceptions.ForbiddenException;
import com.demyanovsky.exceptions.IncorrectEmailException;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.security.EmailSender;
import com.demyanovsky.services.PagingAndSortingHelper;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    @Value("${emailProvider}")
    private String emailProvider;
    @Value("${emailProviderPassword}")
    private String emailProviderPassword;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Page<User> getAll(Integer page, Integer limit) {
        return userRepository.findAll(PagingAndSortingHelper.createPageRequest(page, limit, "name"));
    }

    @Override
    public User getById(UUID id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public User modify(UserDTO userDTO, UUID id) {
        Objects.requireNonNull(userDTO);
        User user = getById(id);
        user.setName(userDTO.getName());
        return userRepository.save(user);
    }

    @Override
    public void restorePassword(EmailDTO emailDTO) {
        User user = userRepository.findByEmail(emailDTO.getEmail());
        if (user == null) {
            throw new IncorrectEmailException(super.toString());
        }
        user.setRestoreHash(UUID.randomUUID().toString());
        user.setValidityPeriod(OffsetDateTime.now(ZoneOffset.UTC).plusHours(48));
        userRepository.save(user);
        EmailSender emailSender = new EmailSender(emailProvider, emailProviderPassword);
        emailSender.send("Restore password", "This is  hashCode for " + user.getRestoreHash() + " restore password",
                emailProvider, emailDTO.getEmail());
    }

    @Override
    public User confirmationPasswordRestore(UserPasswordRestoreDTO userPasswordRestoreDTO, String hash) {
        if (userPasswordRestoreDTO.getPassword().equals(userPasswordRestoreDTO.getConfirmPassword())) {
            try {
                User user = userRepository.findByRestoreHash(hash);
                user.setPassword(bCryptPasswordEncoder.encode(userPasswordRestoreDTO.getPassword() + user.getSalt()));
                return userRepository.save(user);
            } catch (NoSuchElementException e) {
                throw new UserNotFoundException("Restore hash is not valid");
            }
        } else {
            throw new ForbiddenException("Passwords are not identical");
        }
    }

    @Override
    public User save(UserDTO userDTO, Role role) {
        Objects.requireNonNull(userDTO);
        Objects.requireNonNull(role);
        User user = new User();
        user.setRole(role);
        user.setName(userDTO.getName());
        user.setSalt(BCrypt.gensalt().toLowerCase());
        user.setEmail(userDTO.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword() + user.getSalt()));
        return userRepository.save(user);
    }

    @Override
    public void deleteById(UUID id) throws UserNotFoundException {
        try {
            userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            userRepository.deleteById(id);
        } catch (NoSuchElementException e) {
            throw new UserNotFoundException(id);
        }
    }
}
