package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {

    private User user2 = new User("Stiv");
    private User user1 = new User("Bill");
    private User user3 = new User("Will");

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Before
    public void init() throws Exception {
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }

    @After
    public void destroy() {
        userRepository.deleteAll();
    }

    @Test
    public void getById() throws UserNotFoundException {
        assertEquals(userService.getById(user1.getId()).getName(), "Bill");
        assertEquals(userService.getById(user2.getId()), user2);
    }

    @Test
    public void getAll() {
        List<User> tmp = userService.getAll();
        assertEquals(tmp.size(), 3);
    }

    @Test
    public void modifyUser() throws UserNotFoundException {
        User user3 = new User("Edvard");
        user3.setId(user1.getId());
        userService.modify(user3);
        User tmp = new User();
        tmp = userService.getById(user1.getId());
        assertEquals(tmp.getName(), "Edvard");
    }

    @Test
    public void deliteUserByID() throws UserNotFoundException {
        userService.deleteById(user3.getId());
        List<User> tmp = new ArrayList();
        tmp = userService.getAll();
        assertEquals(tmp.size(), 2);
    }
}
