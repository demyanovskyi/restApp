package com.demyanovsky.controllers;

import com.demyanovsky.domain.User;
import com.demyanovsky.services.CRUDConstants;
import com.demyanovsky.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.Iterator;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    // Get all users controller
    @RequestMapping(value = CRUDConstants.GET_ALL_USERS, method = RequestMethod.GET)
    public ResponseEntity<ArrayList<User>> listAllUsers() {
        ArrayList<User> users = userService.getAll();
        return new ResponseEntity<ArrayList<User>>(users, HttpStatus.OK);
    }


    // Get user by id controller
    @RequestMapping(value = CRUDConstants.GET_USER, method = RequestMethod.GET)
    public ResponseEntity<User> userById(@PathVariable("id") int id) {
        User user = userService.getById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }


    // Delete controller
    @RequestMapping(value = CRUDConstants.DELETE_USER, method = RequestMethod.DELETE)
    public ResponseEntity deleteUserById(@PathVariable("id") int id) {
        userService.deliteById(id);
        return new ResponseEntity<User>(HttpStatus.OK);

    }


    //Add new user controller
    @RequestMapping(value = CRUDConstants.CREATE_USER, method = RequestMethod.POST)
    public ResponseEntity<User> createNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<User>(user, HttpStatus.CREATED);
    }

}