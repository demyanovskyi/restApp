package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.UserController;
import com.demyanovsky.domain.User;
import com.demyanovsky.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

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

    static User user2 = new User("Stiv");
    static User user1 = new User("Bill");

    @Test
    public void userById() throws Exception {

        User tmp = userService.save(user2);
        User tmp1 = userService.save(user1);
        mockMvc.perform(get("/user/{id}", tmp.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"name\":\"Stiv\"}"))
                .andReturn();

        mockMvc.perform(get("/user/{id}", tmp1.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andExpect(content().string("{\"id\":\"" + tmp1.getId() + "\",\"name\":\"Bill\"}"))
                .andReturn();
        userService.deleteById(tmp.getId());
        userService.deleteById(tmp1.getId());
    }

    @Test
    public void listAllUsers() throws Exception {
        mockMvc.perform(get("http://localhost:8080//user/"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("listAllUsers")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }

    @Test
    public void deleteUserById() throws Exception {
        User tmp = userService.save(user2);
        User tmp1 = userService.save(user1);
        mockMvc.perform(delete("/user/{id}", tmp.getId()))
                .andExpect(handler().methodName("deleteUserById"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class));
        mockMvc.perform(delete("/user/{id}", tmp1.getId()))
                .andExpect(handler().methodName("deleteUserById"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class));
    }

    @Test
    public void modifyUser() throws Exception {
        User tmp = userService.save(user2);
        mockMvc.perform(put("/user/{id}", tmp.getId()).content("{ \"id\" : \"" + tmp.getId() + "\",\"name\":\"Edvard\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("modifyUser"))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"id\":\"" + tmp.getId() + "\",\"name\":\"Edvard\"}"))
                .andReturn();
        userService.deleteById(tmp.getId());
    }
}
