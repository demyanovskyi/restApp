package com.demyanovsky.services;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;

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
    void deleteById(UUID id) throws UserNotFoundException;
    /**
     * Get the list of users.
     *
     * @return list of users
     */
    Iterable<User> getAll();
    /**
     * Get the User by id.
     *
     * @param id
     * @return user
     */
    User getById(UUID id) throws UserNotFoundException;
    /**
     * Update the User.
     *
     * @param user
     * @return user
     */
    void modify(User user);

}
