package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.UserController;
import com.demyanovsky.domain.User;
import com.demyanovsky.services.UserService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootUsersApplicationTests {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserService userService;

    static final UUID FIRST_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad1");
    static final UUID SECOND_USER_ID = UUID.fromString("5231b533-ba17-4787-98a3-f2df37de2ad2");
    static User user2 = new User(SECOND_USER_ID, "Stiv");
    static User user1 = new User(FIRST_USER_ID, "Bill");

    @Before
    public void init() throws Exception {
        userService.save(user2);
        userService.save(user1);
    }

    @After
    public void destroy() throws Exception {
        userService.deleteById(FIRST_USER_ID);
        userService.deleteById(SECOND_USER_ID);
    }

    @Test
    public void userById() throws Exception {
        mockMvc.perform(get("/user/{id}", SECOND_USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andReturn();
        mockMvc.perform(get("/user/{id}", FIRST_USER_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andReturn();
    }

    @Test
    public void listAllUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080//user/"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("listAllUsers")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void modifyUser() throws Exception {
        mockMvc.perform(put("/user/{id}", SECOND_USER_ID).content("{ \"id\" : \"" + SECOND_USER_ID + "\",\"name\":\"Edvard\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("modifyUser"))
                .andExpect(status().isAccepted())
                .andExpect(content().string("{\"id\":\"" + SECOND_USER_ID + "\",\"name\":\"Edvard\"}"))
                .andReturn();
    }

    @Test
    public void deleteUserById() throws Exception {
        mockMvc.perform(delete("/user/{id}", SECOND_USER_ID))
                .andExpect(handler().methodName("deleteUserById"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class));
        mockMvc.perform(delete("/user/{id}", FIRST_USER_ID))
                .andExpect(handler().methodName("deleteUserById"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class));
    }
}
