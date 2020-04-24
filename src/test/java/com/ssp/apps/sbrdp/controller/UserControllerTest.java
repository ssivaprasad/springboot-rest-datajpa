package com.ssp.apps.sbrdp.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssp.apps.sbrdp.dto.User;
import com.ssp.apps.sbrdp.service.UserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    private static final String APPLICATION_JSON_VALUE = MediaType.APPLICATION_JSON_VALUE;

    @Autowired
    private MockMvc mockmvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = new ArrayList<User>();
        users.add(new User("123", "Somarouthu", "dummy@Test.com"));
        when(userService.getAllUsers()).thenReturn(users);

        mockmvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name").value("Somarouthu"))
                .andExpect(jsonPath("$.[0].email").value("dummy@Test.com"));

    }

    @Test
    public void testGetAllUsers_should_be_empty() throws Exception {
        when(userService.getAllUsers()).thenReturn(null);

        mockmvc.perform(get("/users")).andExpect(status().isOk())
                .andExpect((ResultMatcher) jsonPath("$").doesNotExist());
    }


    @Test
    public void testSave() throws Exception {
        User userResponse = new User("xxxxx", "Siva", "lsdhf");
        User userRequest = new User(null, "Somarouthu", "lsdhf");

        when(userService.createUser(any(User.class))).thenReturn(userResponse);

        MockHttpServletRequestBuilder request = post("/users").contentType(APPLICATION_JSON_VALUE)
                .content(asJsonValue(userRequest)).accept(APPLICATION_JSON_VALUE);

        mockmvc.perform(request).andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Siva"));
    }


    @Test
    public void deleteUser() throws Exception {
        doNothing().when(userService).deleteUser(any(String.class));
        mockmvc.perform(delete("/users/123").contentType(APPLICATION_JSON_VALUE)
                .accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    @Test
    public void updateUser() throws Exception {
        doNothing().when(userService).updateUser(any(User.class));
        mockmvc.perform(put("/users/123").contentType(APPLICATION_JSON_VALUE)
                .content(asJsonValue(new User("xxxxx", "Siva", "lsdhf")))
                .accept(APPLICATION_JSON_VALUE)).andExpect(status().isOk())
                .andExpect(jsonPath("$").doesNotExist());
    }

    private String asJsonValue(User user) throws JsonProcessingException {
        return objectMapper.writeValueAsString(user);
    }

}
