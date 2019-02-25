package com.demyanovsky.controllers;


import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.exceptions.ForbiddenException;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.security.AccessControlHelper;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.mappingConstants.UserCRUDConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @RequestMapping(value = UserCRUDConstants.GET_ALL_USERS, method = RequestMethod.GET)
    private ResponseEntity<List<User>> listAllUsers() {
        logger.info("Call method listAllUsers from UserController");
        List<User> users = userService.getAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(value = UserCRUDConstants.GET_USER, method = RequestMethod.GET)
    private ResponseEntity<User> userById(@PathVariable("id") UUID id) {
        logger.info("Call method userById from UserController");
        User user = userService.getById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = UserCRUDConstants.DELETE_USER, method = RequestMethod.DELETE)
    private ResponseEntity<User> deleteUserById(@PathVariable("id") UUID id) {
        logger.info("Call method deleteUserById from UserController");
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = UserCRUDConstants.CREATE_USER, method = RequestMethod.POST)
    private ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO) {
        logger.info("Call method createNewUser from UserController");
        User user = userService.save(userDTO, Role.USER_ROLE);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @RequestMapping(value = UserCRUDConstants.UPDATE_USER, method = RequestMethod.PUT)
    private ResponseEntity<User> modifyUser(@PathVariable("id") UUID id, @RequestBody UserDTO userDTO) {
        logger.info("Call method modifyUser from UserController");
        if (!userDTO.getId().equals(id)) {
            throw new IncorrectUserException(userDTO.getId());
        }
        if (AccessControlHelper.getRole().equals(Role.ADMIN_ROLE.toString()) || id.equals(AccessControlHelper.getId())) {
            return new ResponseEntity<>(userService.modify(userDTO, id), HttpStatus.OK);
        } else {
            throw new ForbiddenException();
        }
    }
}