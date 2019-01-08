package com.demyanovsky.services;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;

import java.sql.SQLException;
import java.util.List;

/**
 * CRUD interface for User
 *
 * @method save(User user)
 * @method deliteById(int id)
 * @method getAll()
 * @method getById(int id)
 */
public interface UserService {

    /**
     * Add the user .
     *
     * @param user
     */
    void save(User user);

    /**
     * Delete the user by it's id.
     *
     * @param id the id
     */
    void deliteById(Long id);

    /**
     * Get the list of users.
     *
     * @return list of users
     */
    List<User> getAll();

    /**
     * Get the User by id.
     *
     * @param id
     * @return user
     */
    User getById(Long id);

    User modify(User user);
}
