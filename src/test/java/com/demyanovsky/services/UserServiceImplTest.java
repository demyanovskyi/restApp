package com.demyanovsky.services;

import com.demyanovsky.repository.UserRepository;
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

import java.util.UUID;

import static org.junit.Assert.*;

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
    private UserRepository userRepository;

    @Before
    public void init() throws Exception {
        String sql = "CREATE TABLE users ( id  UUID  PRIMARY KEY, name   VARCHAR(100)  NOT NULL);";
        jdbcTemplate.execute(sql);
        userRepository.save(user1);
        userRepository.save(user2);
    }

    @After
    public void destroy() {
        String sql = "DROP TABLE users";
        jdbcTemplate.execute(sql);
    }

    @Test
    public void getAll() {
        assertEquals(userRepository.getAll().size(), 2);
    }

    @Test
    public void getById() {
        assertEquals(userRepository.getUserById(FIRST_USER_ID), user1);
        assertEquals(userRepository.getUserById(SECOND_USER_ID), user2);
    }

    @Test
    public void deliteUserByID() {
        userRepository.deleteUserByID(SECOND_USER_ID);
        assertEquals(userRepository.getAll().size(), 1);
    }

    @Test
    public void modifyUser() {
        User user3 = new User(FIRST_USER_ID, "Edvard");
        userRepository.modify(user3);
        assertEquals(userRepository.getUserById(FIRST_USER_ID).getName(), "Edvard");
    }
}
