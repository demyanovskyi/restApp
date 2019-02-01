package com.demyanovsky.services;

import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
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

    static private UUID corrId = UUID.randomUUID();
    static User user2 = new User("Stiv");
    static User user1 = new User("Bill");
    static User user3 = new User("Will");

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserService userService;

    @Before
    public void init() throws Exception {
     /*   String sql1 = "DROP TABLE users";
        jdbcTemplate.execute(sql1);*/
        String sql = "CREATE TABLE IF NOT EXISTS  users ( id UUID  NOT NULL PRIMARY KEY , name   VARCHAR(100)  NOT NULL);";
        jdbcTemplate.execute(sql);
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
    }

    @Test
    public void getById() throws UserNotFoundException {
        assertEquals(userService.getById(user1.getId()).getName(), "Bill");
        assertEquals(userService.getById(user2.getId()), user2);
    }

    /*
        @After
        public void destroy() {
            String sql = "DROP TABLE users";
            jdbcTemplate.execute(sql);
        }
    */
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
        tmp = (List<User>) userService.getAll();
        assertEquals(tmp.size(), 2);
    }
}
