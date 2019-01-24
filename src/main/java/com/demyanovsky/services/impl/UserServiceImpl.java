package com.demyanovsky.services.impl;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserDataRepository;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service

public class UserServiceImpl implements UserService {
    /**
     *
     */
    @Autowired
    private UserDataRepository userRepository;

    private static List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public Optional<User> getById(UUID id) {
        try {
            return userRepository.findById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User modify(User user) {

return null;

    }
    @Override

    public User save(User user) {

    return   userRepository.save(user);

    }


    @Override
    public void deleteById(UUID id) {
 userRepository.deleteById(id);

    }
}
