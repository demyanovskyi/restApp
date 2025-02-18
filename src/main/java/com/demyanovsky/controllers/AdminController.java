package com.demyanovsky.controllers;

import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.mappingConstants.UserCRUDConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @RequestMapping(value = UserCRUDConstants.CREATE_ADMIN, method = RequestMethod.POST)
    private ResponseEntity<User> createNewUser(@RequestBody UserDTO userDTO) {
        logger.info("Call method createNewUser from AdminController");
        User user = userService.save(userDTO, Role.ADMIN_ROLE);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
