package com.demyanovsky.controllers;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.services.UserCRUDConstants;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    /**
     * Private final constructor
     */
    private final UserService userService;

    @Autowired
    private UserController(UserService userService) {
        this.userService = userService;
    }

    // Get all users controller
    @RequestMapping(value = UserCRUDConstants.GET_ALL_USERS, method = RequestMethod.GET)
    private ResponseEntity<List<User>> listAllUsers() throws SQLException {
        List<User> users = userService.getAll();
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    // Get user by id controller
    @RequestMapping(value = UserCRUDConstants.GET_USER, method = RequestMethod.GET)
    private ResponseEntity<User> userById(@PathVariable("id") UUID id) {
        User user = userService.getById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // Delete controller
    @RequestMapping(value = UserCRUDConstants.DELETE_USER, method = RequestMethod.DELETE)
    private ResponseEntity deleteUserById(@PathVariable("id") UUID id) {
        userService.deleteById(id);
        return new ResponseEntity<User>(HttpStatus.OK);
    }

    //Add new user controller
    @RequestMapping(value = UserCRUDConstants.CREATE_USER, method = RequestMethod.POST)
    private ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

    //Modify user controller
    @RequestMapping(value = UserCRUDConstants.UPDATE_USER, method = RequestMethod.PUT)
    private ResponseEntity modifyUser(@PathVariable("id") UUID id, @RequestBody User user) {
        if (!user.getId().equals(id)) {
            throw new UserNotFoundException(id);
        } else {
            userService.modify(user);
            return new ResponseEntity<User>(user, HttpStatus.OK);
        }
    }
}
