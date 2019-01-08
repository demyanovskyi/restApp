package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.UserController;
import com.demyanovsky.services.UserService;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootUsersApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void init() throws Exception {
        mockMvc.perform(post("/user/").content("{ \"id\" : 12 , \"name\" : \"Stiv\"}").contentType(MediaType.APPLICATION_JSON_UTF8)
        );
        mockMvc.perform(post("/user/").content("{ \"id\" : 10 , \"name\" : \"Bob\"}").contentType(MediaType.APPLICATION_JSON_UTF8)
        );
      
    }
    @After
    public void destroy() throws Exception {
        mockMvc.perform(delete("/user/12")
        );

        mockMvc.perform(delete("/user/10")
        );
    }

    @Test
    public void userById() throws Exception {
        mockMvc.perform(get("/user/{id}", 12))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andReturn();
        mockMvc.perform(get("/user/{id}", 10))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andReturn();

    }

    @Test
    public void listAllUsers() throws Exception {
        mockMvc
                .perform(get("http://localhost:8080//user/"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("listAllUsers")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));




    }
    @Test
    public void deleteUserById() throws Exception {
        mockMvc
                .perform(delete("http://localhost:8080//user/10"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("deleteUserById")).andExpect(status().isOk());


    }

}



