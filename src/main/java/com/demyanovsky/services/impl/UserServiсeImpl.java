package com.demyanovsky.services.impl;

import com.demyanovsky.dao.UserDao;
import com.demyanovsky.domain.User;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class UserServiсeImpl implements UserService {


    @Autowired
    UserDao userDao;


    private static List<User> users = new ArrayList<>();


    public static List<User> getUsers() {
        return users;
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }


    @Override
    public User getById(long id) {
        return  userDao.getUserById(id);

    }

    @Override
    public User modify(User user) {
        //TODO
        return null;
    }


    @Override
    public User save(User user) {
userDao.save(user);
        return  user;


    }


    @Override
    public void deliteById(long id) {
userDao.deliteUsebyIDr(id);
return;

    }
}

