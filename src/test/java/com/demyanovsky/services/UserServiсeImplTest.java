package com.demyanovsky.services;

import com.demyanovsky.dao.UserDao;
import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.services.impl.UserServiсeImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;

import static org.junit.Assert.*;

public class UserServiсeImplTest {
    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiсeImpl();
        }
    }

    @Autowired
    private UserService userService;

    @MockBean
    private UserDao userDao;

/*
    @Mock
    UserService userService = new UserServiсeImpl();
*/
@Before
public void setUp() {
    User alex = new User((long) 2,"alex");
    userDao.save(alex);

}

    @Test
    public void getAll() {
    /*    User user1 = new User((long) 222, "Bill");
        User user2 = new User((long) 7722, "Stiv");
        User user3 = new User((long) 332, "Ivan");
        User user4 = new User((long) 132, "Andy");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);*/
        Assert.assertNotNull(userService);
        assertEquals(userService.getAll().size(), 1);
    }

    @Test
    public void getById() {
        User user1 = new User((long) 22, "Bill");
        User user2 = new User((long) 772, "Stiv");
        User user3 = new User((long) 33, "Ivan");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        assertNotNull(userService);
        assertEquals(userService.getById((long) 22), user1);
        assertEquals(userService.getById((long) 772), user2);
        assertEquals(userService.getById((long) 33), user3);


    }


    @Test
    public void deliteById() {
        User user1 = new User((long) 42, "Ivan");
        userService.save(user1);
        assertEquals(userService.getById((long) 42), user1);
        userService.deliteById((long) 42);
        try {
            userService.getById((long) 42);
        } catch (UserNotFoundException id) {
        }

    }
}
