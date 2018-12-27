package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.UserController;
import com.demyanovsky.domain.User;
import com.demyanovsky.services.UserService;
import com.demyanovsky.services.UserServiсeImpl;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootUsersApplicationTests {

    @Autowired
    private MockMvc mockMvc;


    @Autowired
    UserService userServiсe;


    @Before
    public void init() throws Exception {
        mockMvc.perform(post("/user/").content("{ \"id\" : 12 , \"name\" : \"aaa\"}").contentType(MediaType.APPLICATION_JSON_UTF8)
        );
        mockMvc.perform(post("/user/").content("{ \"id\" : 10 , \"name\" : \"aaa\"}").contentType(MediaType.APPLICATION_JSON_UTF8)
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
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))

        ;

    }
    @Test
    public void deleteUserById() throws Exception {
        mockMvc
                .perform(delete("http://localhost:8080//user/10"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("deleteUserById")).andExpect(status().isOk());


    }

}



