package com.demyanovsky.controllers;

import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.mappingConstants.UserCRUDConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

@RestController
public class AdminController {
    @Autowired
    UserService userService;

    @RequestMapping(value = UserCRUDConstants.CREATE_ADMIN, method = RequestMethod.POST)
    private ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        User user = userService.save(userDTO, Role.ADMIN);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
