package com.demyanovsky.services.impl;


import com.demyanovsky.dao.UserDao;
import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public  class UserServiсeImpl implements UserService {

    @Autowired
    UserDao userDao;


    private static List<User> users = new ArrayList<>();


    public static List<User> getUsers() {
        return users;
    }

    @Override
    public List<User> getAll(){
        return userDao.getAll();
    }


    @Override
    public User getById(Long id)  {
        try {
            return userDao.getUserById(id);
        } catch (Exception e) {
            throw  new UserNotFoundException(id);
        }

    }


    @Override
    public User modify(User user) {
        //TODO
        return null;
    }


    @Override
    public void save(User user) {
        try {
            if (user.getId()!=null&&user.getName()!=null){
                userDao.save(user);}
        } catch (Exception e) {
            throw  new IncorrectUserException(user.getId());

        }
    }



    @Override
    public void deliteById(Long id){
        try {
            if (userDao.getUserById(id)!=null)
                userDao.deliteUsebyID(id);
        }catch (Exception e){
            throw new UserNotFoundException(id);
        }

    }
}
