package com.demyanovsky.services.impl;


import com.demyanovsky.domain.*;
import com.demyanovsky.exceptions.ForbiddenException;
import com.demyanovsky.exceptions.IncorrectEmailException;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.security.EmailSender;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {
    private static final String REGEX = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle("application-email");

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(list::add);
        return list;
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
    public MessageDTO restorePassword(EmailDTO emailDTO) {
        User user = userRepository.findByEmail(emailDTO.getEmail());
        if (user == null) {
            throw new IncorrectEmailException(super.toString());
        }
        String restoreHash = UUID.randomUUID().toString();
        user.setRestoreHash(restoreHash);
        LocalDateTime validityPeriod = LocalDateTime.now().plusHours(48);
        user.setValidityPeriod(validityPeriod);
        userRepository.save(user);
        EmailSender emailSender = new EmailSender(resourceBundle.getString("emailProvider"), resourceBundle.getString("emailProviderPassword"));
        emailSender.send("Restore password", "This is  hashCode for " + user.getRestoreHash() + " restore password",
                resourceBundle.getString("emailProvider"), emailDTO.getEmail());
        MessageDTO dto = new MessageDTO();
        dto.setMessage("Secret code to restore your password sent to your email");
        return dto;
    }

    @Override
    public User confirmationPasswordRestore(UserPasswordRestoreDTO userPasswordRestoreDTO, String hash) {
        if (userPasswordRestoreDTO.getPassword().equals(userPasswordRestoreDTO.getConfirmPassword())) {
            try {
                User user = userRepository.findByRestoreHash(hash);
                user.setPassword(bCryptPasswordEncoder.encode(userPasswordRestoreDTO.getPassword() + user.getSalt()));
                return userRepository.save(user);
            } catch (NoSuchElementException e) {
                throw new UserNotFoundException("Restore hash");
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
        if (validEmail(userDTO.getEmail())) {
            user.setRole(role);
            user.setName(userDTO.getName());
            user.setSalt(BCrypt.gensalt().toLowerCase());
            user.setEmail(userDTO.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword() + user.getSalt()));
        } else {
            throw new ForbiddenException("Incorrect email");
        }
        return userRepository.save(user);
    }

    public boolean validEmail(String email) {
        return email.matches(REGEX);
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

