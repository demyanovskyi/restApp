package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserIdNotContainException;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.exceptions.UserWithSuchIdAlreadyExistsException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class UserServiсeImplTest {
    @Mock
    UserServiсeImpl userService = new UserServiсeImpl();


    @Test
    public void getAll() {
        User user1 = new User(222, "Bill");
        User user2 = new User(7722, "Stiv");
        User user3 = new User(332, "Ivan");
        User user4 = new User(132, "Andy");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        Assert.assertNotNull(userService);
        assertEquals(userService.getAll().size(), 4);
    }

    @Test
    public void getById() {
        User user1 = new User(22, "Bill");
        User user2 = new User(772, "Stiv");
        User user3 = new User(33, "Ivan");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        assertNotNull(userService);
        assertEquals(userService.getById(22), user1);
        assertEquals(userService.getById(772), user2);
        assertEquals(userService.getById(33), user3);


    }


    @Test
    public void deliteById() {
        User user1 = new User(42, "Ivan");
        userService.save(user1);
        assertEquals(userService.getById(42), user1);
        userService.deliteById(42);
        try {
            userService.getById(42);
        } catch (UserNotFoundException id) {
        }

    }
}