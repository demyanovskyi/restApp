package com.demyanovsky.services.impl;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

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
    public void modify(User user) {
        Objects.requireNonNull(user);
        userRepository.save(user);
    }

    @Override
    public User save(User user) {
        Objects.requireNonNull(user);
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
