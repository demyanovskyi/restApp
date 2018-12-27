package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import java.util.List;

/**
 * CRUD interface for User
 * @method  save(User user)
 * @method  deliteById(int id)
 * @method  getAll()
 * @method  getById(int id)
 */
public interface UserService {

    /**
     * Add the user .
     * @param user
     */
    User save(User user);

    /**
     * Delete the user by it's id.
     * @param id the id
     */
    void deliteById(int id);

    /**
     * Get the list of users.
     * @return list of users
     */
    List<User> getAll();

    /**
     * Get the User by id.
     * @param id
     * @return user
     */
    User getById(int id);

    User modify(User user);
}
