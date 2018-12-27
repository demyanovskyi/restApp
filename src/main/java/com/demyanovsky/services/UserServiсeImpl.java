package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.exceptions.UserWithSuchIdAlreadyExistsException;
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
    public List<User> getAll() {
        return  users;
    }


    @Override
    public User getById(int id) {
        for (User user : users) {
            if (user.getId() == id) {
                return user;
            }
        }
        throw new UserNotFoundException(id);
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
                    throw new UserWithSuchIdAlreadyExistsException(user);
                }
            }
        users.add(user);
        return user;
    }

    @Override
    public void deliteById(int id) {
      try {
          User user1 = getById(id);
      }catch (UserNotFoundException e){
          throw new UserNotFoundException(id);
      }
       /* if(getById(id) == null) {
            throw new UserIdNotContainException(id);
        }*/
        for (Iterator<User> iterator = users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            if (user.getId() == id) {
                iterator.remove();
            }
        }

    }
}

