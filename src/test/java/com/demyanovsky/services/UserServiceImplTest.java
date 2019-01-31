package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {
    static final UUID FIRST_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad2");
    static private UUID corrId = UUID.randomUUID();
    static User user2 = new User(FIRST_USER_ID, "Stiv");
    static User user1 = new User(SECOND_USER_ID, "Bill");

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Before
    public void init() throws Exception {

        String sql = "CREATE TABLE users ( id UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY , name   VARCHAR(100)  NOT NULL);";
        jdbcTemplate.execute(sql);
          userService.save(user1);
        User tmp1 = userService.save(user2);
    }

    @After
    public void destroy() {
        String sql = "DROP TABLE users";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void getAll() {
        List<User> tmp = userService.getAll();
        assertEquals(tmp.size(), 2);
    }

    @Test
    public void getById() throws UserNotFoundException {
        assertEquals(userService.getById(user1.getId()), user1);
        assertEquals(userService.getById(user2.getId()), user2);
    }

    @Test
    public void deliteUserByID() throws UserNotFoundException {
        userService.deleteById(SECOND_USER_ID);
        List<User> tmp = new ArrayList();
        tmp = (List<User>) userService.getAll();
        assertEquals(tmp.size(), 1);
    }

    @Test
    public void modifyUser() throws UserNotFoundException {
        User user3 = new User("Edvard");
        userService.modify(user3);
        User tmp = new User();
        tmp = userService.getById(FIRST_USER_ID);
        assertEquals(tmp.getName(), "Edvard");
    }
}
