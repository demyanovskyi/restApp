package com.demyanovsky.services;


import com.demyanovsky.domain.*;
import com.demyanovsky.exceptions.UserNotFoundException;

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
     * @param userDTO
     */
    User save(UserDTO userDTO, Role role);

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
    List<User> getAll();

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
     * @param userDTO
     * @return user
     */
    User modify(UserDTO userDTO, UUID id);

    /**
     * Request for restore password.
     *
     * @param emailDTO
     */
    void restorePassword(EmailDTO emailDTO);

    /**
     * Confirm restore password.
     *
     * @param userPasswordRestoreDTO
     * @return User
     */
    User confirmationPasswordRestore(UserPasswordRestoreDTO userPasswordRestoreDTO, String hash);


}
