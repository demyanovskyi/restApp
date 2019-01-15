package com.demyanovsky.services.impl;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    private static List<User> users = new ArrayList<>();

    public static List<User> getUsers() {
        return users;
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public User getById(UUID id) {
        try {
            return userRepository.getUserById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public User modify(User user) {
try {
    userRepository.modify(user);
}catch (Exception e){
            throw new IncorrectUserException(user.getId());
        }
        return userRepository.getUserById(user.getId());
    }

    @Override
    public void save(User user) {
        try {
            if (user.getId() != null && user.getName() != null) {
                userRepository.save(user);
            }
        } catch (Exception e) {
            throw new IncorrectUserException(user.getId());
        }
    }

    @Override
    public void deleteById(UUID id) {
        try {
            userRepository.deleteUserByID(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }
}
