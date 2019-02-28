package com.demyanovsky.services;

import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.exceptions.UserNotFoundException;
import com.demyanovsky.repository.UserRepository;
import org.junit.After;
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
public class UserServiceTest {

    private User user2 = new User("Stiv", "423", "fdsf@gmail.fw");
    private User user1 = new User("Bill", "fdr", "qwe@gmail.dc");
    private User user3 = new User("Will", "r324", "3d1@gmail.com");

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @After
    public void destroy() {
        userRepository.deleteAll();
    }

    @Test
    public void getById() throws UserNotFoundException {
        User user = userService.save(new UserDTO(user1.getName(), user1.getEmail(), user1.getPassword()), Role.USER_ROLE);
        User user1 = userService.save(new UserDTO(user2.getName(), user2.getEmail(), user2.getPassword()), Role.USER_ROLE);
        assertEquals(userService.getById(user.getId()).getName(), "Bill");
        assertEquals(userService.getById(user1.getId()), user1);
    }

    @Test
    public void getAll() {
        User user = userService.save(new UserDTO(user1.getName(), user1.getEmail(), user1.getPassword()), Role.USER_ROLE);
        User user1 = userService.save(new UserDTO(user2.getName(), user2.getEmail(), user2.getPassword()), Role.USER_ROLE);
        List<User> tmp = userService.getAll(0, 2);
        assertEquals(tmp.size(), 2);
    }

    @Test
    public void modifyUser() throws UserNotFoundException {
        User user1 = userService.save(new UserDTO(user3.getName(), user3.getEmail(), user3.getPassword()), Role.USER_ROLE);
        user1.setName("Edvard");
        userService.modify(new UserDTO(user1.getName()), user1.getId());
        User tmp = new User();
        tmp = userService.getById(user1.getId());
        assertEquals(tmp.getName(), "Edvard");
    }

    @Test
    public void deleteUserByID() throws UserNotFoundException {
        User user1 = userService.save(new UserDTO(user3.getName(), user3.getEmail(), user3.getPassword()), Role.USER_ROLE);
        User user2 = userService.save(new UserDTO(user1.getName(), user1.getEmail(), user1.getPassword()), Role.USER_ROLE);
        User user4 = userService.save(new UserDTO(user2.getName(), user2.getEmail(), user2.getPassword()), Role.USER_ROLE);
        userService.deleteById(user1.getId());
        List<User> tmp = new ArrayList();
        tmp = userService.getAll(0, 2);
        assertEquals(tmp.size(), 2);
    }
}
