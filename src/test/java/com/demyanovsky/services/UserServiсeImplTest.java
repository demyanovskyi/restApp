package com.demyanovsky.services;


import com.demyanovsky.repository.impl.UserDaoImpl;
import com.demyanovsky.domain.User;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.services.impl.UserServiсeImpl;
import org.junit.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;


import static org.junit.Assert.*;

@ActiveProfiles("test")
public class UserServiсeImplTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    private UserDaoImpl userDao;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

/*
    @Bean
    public DataSource dataSource(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/user");
        dataSource.setUsername( “user" );
        dataSource.setPassword( “root" );
        return dataSource;
    }*/


    @Mock
    UserService userService = new UserServiсeImpl();


    @Test
    public void getAll() {

       /* User user1 = new User((long) 222, "Bill");
        User user2 = new User((long) 7722, "Stiv");
        User user3 = new User((long) 332, "Ivan");
        User user4 = new User((long) 132, "Andy");
        userService.save(user1);
        userService.save(user2);
        userService.save(user3);
        userService.save(user4);
        Assert.assertNotNull(userService);
        assertEquals(userService.getAll().size(), 1);*/

   /*     List<User> list = userDao.getAll();
        Assert.assertTrue(list.size() == 4);*/
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
