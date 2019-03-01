package com.demyanovsky.controllers;


import com.demyanovsky.domain.*;
import com.demyanovsky.exceptions.ForbiddenException;
import com.demyanovsky.exceptions.IncorrectEmailException;
import com.demyanovsky.exceptions.IncorrectUserException;
import com.demyanovsky.security.AccessControlHelper;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.mappingConstants.UserCRUDConstants;
import org.hibernate.annotations.NamedNativeQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
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
    private ResponseEntity<Page<User>> listAllUsers(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        logger.info("Call method listAllUsers from UserController");
        Page<User> users = userService.getAll(page, limit);
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
        if (AccessControlHelper.getRole().toString().equals(Role.ADMIN_ROLE.toString()) || id.equals(AccessControlHelper.getId())) {
            return new ResponseEntity<>(userService.modify(userDTO, id), HttpStatus.OK);
        } else {
            throw new ForbiddenException();
        }
    }

    @RequestMapping(value = UserCRUDConstants.PASSWORD_RESTORE, method = RequestMethod.POST)
    private void passwordRestore(@RequestBody EmailDTO emailDTO) {
        try {
            userService.restorePassword(emailDTO);
        } catch (IncorrectUserException e) {
            throw new IncorrectEmailException(emailDTO.getEmail());
        }
    }

    @RequestMapping(value = UserCRUDConstants.CONFIRMATION_PASSWORD_RESTORE, method = RequestMethod.POST)
    private ResponseEntity<User> confirmationPasswordRestore(@PathVariable("hash") String hash, @RequestBody UserPasswordRestoreDTO userPasswordRestoreDTO) {
        User user = userService.confirmationPasswordRestore(userPasswordRestoreDTO, hash);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}