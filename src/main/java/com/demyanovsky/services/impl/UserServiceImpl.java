package com.demyanovsky.services.impl;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User getById(UUID id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    @Override
    public void modify(User  user) {
try {
    userRepository.save(user);
}catch (NoSuchElementException e){
    throw new IncorrectUserException(user.getId());
}
    }

    @Override
    public User save(User user) {
        try {
            return userRepository.save(user);
        } catch (NoSuchElementException e) {
            throw new IncorrectUserException(user.getId());
        }
    }

    @Override
    public void deleteById(UUID id) throws UserNotFoundException {
        try {
            // userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
            userRepository.deleteById(id);
        } catch (Exception e) {
            throw new UserNotFoundException(id);
        }
    }
}
