package com.demyanovsky.services;

import com.demyanovsky.domain.User;
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
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {
    static final UUID FIRST_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad2");
    static User user2 = new User(SECOND_USER_ID, "Stiv");
    static User user1 = new User(FIRST_USER_ID, "Bill");

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Before
    public void init() throws Exception {
        String sql = "CREATE TABLE users ( id  UUID  PRIMARY KEY, name   VARCHAR(100)  NOT NULL);";
        jdbcTemplate.execute(sql);
        userService.save(user1);
        userService.save(user2);
    }

    @After
    public void destroy() {
        String sql = "DROP TABLE users";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void getAll() {
        List<User> tmp = new ArrayList();
        tmp = (List<User>) userService.getAll();
        assertEquals(tmp.size(), 2);
    }

    @Test
    public void getById() {
        assertEquals(userService.getById(FIRST_USER_ID).get(), user1);
        assertEquals(userService.getById(SECOND_USER_ID).get(), user2);
    }

    @Test
    public void deliteUserByID() {
        userService.deleteById(SECOND_USER_ID);
        List<User> tmp = new ArrayList();
        tmp = (List<User>) userService.getAll();
        assertEquals(tmp.size(), 1);
    }

    @Test
    public void modifyUser() {
        User user3 = new User(FIRST_USER_ID, "Edvard");
        userService.modify(user3);
        Optional<User> tmp  = Optional.of(new User());
        tmp = userService.getById(FIRST_USER_ID);
        assertEquals(tmp.get().getName(), "Edvard");
    }
}
