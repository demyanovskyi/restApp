package com.demyanovsky.services;


import com.demyanovsky.domain.User;

import java.util.List;
import java.util.UUID;

/**
 * CRUD interface for User
 *
 * @method save(User user)
 * @method deliteById(int id)
 * @method getAll()
 * @method getById(int id)
 * @method modify(User user)
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
    void deleteById(UUID id);
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
    User getById(UUID id);
    /**
     * Update the User.
     *
     * @param user
     * @return user
     */
    User modify(User user);
}
