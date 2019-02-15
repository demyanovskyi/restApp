package com.demyanovsky.services.impl;


import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.security.SHA256Generator;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SHA256Generator sha256Generator;

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
    public User save(UserDTO userDTO, Role role) throws  NoSuchAlgorithmException {
        Objects.requireNonNull(userDTO);
        Objects.requireNonNull(role);
        User user = new User();
        user.setRole(role);
        user.setName(userDTO.getName());
        user.setSalt(DatatypeConverter.printHexBinary(getSalt()).toLowerCase());
        user.setPassword(sha256Generator.getHashPassword(userDTO.getPassword(), user.getSalt()));
        return userRepository.save(user);
    }

    private static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
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

