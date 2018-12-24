package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.junit.Assert.*;

public class UserServiсeImplTest {
    @Mock
    UserServiсeImpl userService = new UserServiсeImpl();


    @Test
    public void getAll() {
        User user1 = new User(22, "Bill");
        User user2 = new User(772, "Stiv");
        User user3 = new User(33, "Ivan");
        User user4 = new User(13, "Andy");
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
        User user4 = new User(13, "Andy");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        assertNotNull(userService);
        assertEquals(userService.getById(22), user1);
        assertEquals(userService.getById(772), user2);
        assertEquals(userService.getById(33), user3);


    }


    @Test
    public void deliteById() {
        userService.save(new User(42, "Ivan"));
        userService.save(new User(331, "Sara"));
        userService.deliteById(42);
        assertNotNull(userService.getById(331));
        userService.deliteById(331);
        assertNull(userService.getById(331));
        assertNull(userService.getById(42));
    }
}