package com.demyanovsky.dao;


import com.demyanovsky.domain.User;

import java.util.List;

public interface UserDao {
    void save(User cus);

    List<User> getAll();

    User getUserById(long id);

    void deliteUsebyIDr(long id);

}

