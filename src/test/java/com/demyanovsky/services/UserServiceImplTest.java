package com.demyanovsky.services;


import com.demyanovsky.repository.UserRepository;
import com.demyanovsky.domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceImplTest {
    static final long FIRSTUSERID = 222;
    static final long SECONDUSERID = 7722;
    @Autowired
    JdbcTemplate jdbcTemplate;
    static User user2 = new User(SECONDUSERID, "Stiv");
    static User user1 = new User(FIRSTUSERID, "Bill");

    @Autowired
    private UserRepository userRepository;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    @Before
    public void init() throws Exception {
        String sql = "CREATE TABLE users ( id  INT  PRIMARY KEY, name   VARCHAR(100)  NOT NULL);";
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
        assertEquals(userRepository.getUserById(FIRSTUSERID), user1);
        assertEquals(userRepository.getUserById(SECONDUSERID), user2);
    }

    @Test
    public void deliteUsebyID() {
        userRepository.deliteUsebyID(SECONDUSERID);
        assertEquals(userRepository.getAll().size(), 1);

    }
}
