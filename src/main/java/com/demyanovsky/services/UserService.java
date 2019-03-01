package com.demyanovsky.services;


import com.demyanovsky.domain.*;
import com.demyanovsky.exceptions.UserNotFoundException;
import org.springframework.data.domain.Page;

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
     * @param page
     * @param limit
     * @return list of users
     */
    Page<User> getAll(Integer page, Integer limit);

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
     * @param id
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
     * @param hash
     * @return User
     */
    User confirmationPasswordRestore(UserPasswordRestoreDTO userPasswordRestoreDTO, String hash);


}
