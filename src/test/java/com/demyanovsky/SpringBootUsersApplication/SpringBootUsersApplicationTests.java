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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class SpringBootUsersApplicationTests {

    /*  private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
              MediaType.APPLICATION_JSON.getSubtype(),
              Charset.forName("utf8"));
  */
    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }


    static RestTemplate restTemplate = new RestTemplate();

    @Autowired
    UserService userServiсe;


    @Before
    public void init() {
        User user = new User(1, "Tomm");
        URI uri = restTemplate.postForLocation("http://localhost:8080/" + "/user/", user, User.class);
        User user1 = new User(2, "Bill");
        URI uri1 = restTemplate.postForLocation("http://localhost:8080/" + "/user/", user1, User.class);
    }


    @Test
    public void userById() throws Exception {

        mockMvc
                .perform(get("/user/{id}", "1"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("userById"))
                .andReturn();
    }

    @Test
    public void listAllUsers() throws Exception {
        ResponseEntity<ArrayList<User>> responsEntity = restTemplate.exchange("http://localhost:8080//user/",
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<User>>() {
                });
        ArrayList<User> actuaalList = responsEntity.getBody();
        Assert.assertEquals(actuaalList.size(), 2);


        mockMvc
                .perform(get("http://localhost:8080//user/"))
                .andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("listAllUsers")).andExpect(status().isOk());

    }

    @Test
    public void deleteUserById() throws Exception {
        ResponseEntity<ArrayList<User>> responsEntity = restTemplate.exchange("http://localhost:8080//user/",
                HttpMethod.GET, null, new ParameterizedTypeReference<ArrayList<User>>() {
                });
        ArrayList<User> actuaalList = responsEntity.getBody();
        Assert.assertEquals(actuaalList.get(1).getName(), "Bill");
        mockMvc
                .perform(delete("http://localhost:8080//user/2")).andExpect(handler().handlerType(UserController.class))
                .andExpect(handler().methodName("deleteUserById")).andExpect(status().isOk());


    }

}



