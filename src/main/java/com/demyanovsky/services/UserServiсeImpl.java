package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Service("userService")
public class UserServiсeImpl implements UserService {


    private static List<User> users = new ArrayList<>();


    public static List<User> getUsers() {
        return users;
    }

    @Override
    public ArrayList<User> getAll() {
        return (ArrayList<User>) users;
    }


    @Override
    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }


    @Override
    public User modify(User user) {
        //TODO
        return null;
    }


    @Override
    public User save(User user) {
        if (user != null)
            for (User temp : users) {
                if (temp.equals(user)) {
                    return null;
                }
            }
        users.add(user);
        return user;
    }

    @Override
    public void deliteById(int id) {


        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }
    }


}

