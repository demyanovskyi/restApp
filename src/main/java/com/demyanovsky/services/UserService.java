package com.demyanovsky.services;

import com.demyanovsky.domain.User;

import java.util.ArrayList;


public interface UserService {

    User save(User user);

    void deliteById(int id);

    ArrayList<User> getAll();

    User getById(int id);

    User modify(User user);
}
