package com.demyanovsky.services;


import com.demyanovsky.domain.User;

import java.util.Optional;
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
    User save(User user);
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
    Iterable<User> getAll();
    /**
     * Get the User by id.
     *
     * @param id
     * @return user
     */
    Optional<User> getById(UUID id);
    /**
     * Update the User.
     *
     * @param user
     * @return user
     */
    User modify(User user);

}
