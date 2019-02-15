package com.demyanovsky.SpringBootUsersApplication;

import com.demyanovsky.controllers.UserController;
import com.demyanovsky.domain.Role;
import com.demyanovsky.domain.User;
import com.demyanovsky.domain.UserDTO;
import com.demyanovsky.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.demyanovsky.services.mappingConstants.UserCRUDConstants.GET_ALL_USERS;
import static org.hamcrest.Matchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    UserService userService;

    private UserDTO user2 = new UserDTO("Stiv", "123526tgf");
    private UserDTO user1 = new UserDTO("Sara", "gwrthg234");

    @Test
    public void userById() throws Exception {
        User testUser = userService.save(user1, Role.USER);
        User testUser1 = userService.save(user2, Role.USER);
        mockMvc.perform(get("/user/{id}", testUser.getId()).with(httpBasic(user1.getName(), user1.getPassword()))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andExpect(jsonPath("$.id", is(testUser.getId().toString())))
                .andExpect(jsonPath("$.name", is(testUser.getName())))
                .andReturn();
        mockMvc.perform(get("/user/{id}", testUser1.getId())
                .with(httpBasic(user2.getName(), user2.getPassword()))
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(handler().methodName("userById"))
                .andExpect(jsonPath("$.id", is(testUser1.getId().toString())))
                .andExpect(jsonPath("$.name", is(testUser1.getName())))
                .andReturn();
        userService.deleteById(testUser.getId());
        userService.deleteById(testUser1.getId());
    }

    @Test
    public void listAllUsers() throws Exception {
        User testUser = userService.save(user1, Role.USER);
        mockMvc.perform(get(GET_ALL_USERS)
                .with(httpBasic(user1.getName(), user1.getPassword())))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("listAllUsers")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        userService.deleteById(testUser.getId());
    }

    @Test
    public void deleteUserById() throws Exception {
        User testUser = userService.save(user1, Role.ADMIN);
        User testUser1 = userService.save(user2, Role.ADMIN);
        mockMvc.perform(delete("/user/{id}", testUser.getId())
                .with(httpBasic(user1.getName(), user1.getPassword())))
                .andExpect(handler().methodName("deleteUserById"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class));
        mockMvc.perform(delete("/user/{id}", testUser1.getId())
                .with(httpBasic(user2.getName(), user2.getPassword())))
                .andExpect(handler().methodName("deleteUserById"))
                .andExpect(status().isOk())
                .andExpect(handler().handlerType(UserController.class));
    }

    @Test
    public void modifyUser() throws Exception {
        User testUser = userService.save(user1, Role.ADMIN);
        mockMvc.perform(put("/user/{id}", testUser.getId()).content("{ \"id\" : \"" + testUser.getId() + "\",\"name\":\"Edvard\",\"password\":\"gwrthg234\"}")
                .with(httpBasic(user1.getName(), user1.getPassword()))
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(handler().methodName("modifyUser"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(testUser.getId().toString())))
                .andExpect(jsonPath("$.name", is("Edvard")))
                .andReturn();
        userService.deleteById(testUser.getId());
    }
}
