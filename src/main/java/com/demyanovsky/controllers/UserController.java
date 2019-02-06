package com.demyanovsky.controllers;


import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.mappingConstants.UserCRUDConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = UserCRUDConstants.GET_ALL_USERS, method = RequestMethod.GET)
    private ResponseEntity<List<User>> listAllUsers() throws SQLException {
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = UserCRUDConstants.GET_USER, method = RequestMethod.GET)
    private ResponseEntity<User> userById(@PathVariable("id") UUID id) throws UserNotFoundException {
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = UserCRUDConstants.DELETE_USER, method = RequestMethod.DELETE)
    private ResponseEntity<User> deleteUserById(@PathVariable("id") UUID id) throws UserNotFoundException {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = UserCRUDConstants.CREATE_USER, method = RequestMethod.POST)
    private ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = UserCRUDConstants.UPDATE_USER, method = RequestMethod.PUT)
    private ResponseEntity modifyUser(@PathVariable("id") UUID id, @RequestBody User user) {
        if (user.getId().equals(id))
            userService.modify(user);
        else {
            throw new IncorrectUserException(user.getId());
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}

