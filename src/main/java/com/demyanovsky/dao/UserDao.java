package com.demyanovsky.dao;


import com.demyanovsky.domain.User;

import java.util.List;

public interface UserDao<T> {
    void save(User cus);

    List<User> getAll();

    User getUserById(Long id);

    void deliteUsebyID(long id);

}
