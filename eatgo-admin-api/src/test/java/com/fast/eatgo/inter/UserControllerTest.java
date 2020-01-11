package com.fast.eatgo.inter;

import com.fast.eatgo.application.UserService;
import com.fast.eatgo.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @Test
    public void list() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(User.builder()
                .email("test@test.com")
                .name("Test1")
                .level(1L)
                .build());

        given(userService.getUsers()).willReturn(users);

        mvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test@test.com")))
                .andExpect(content().string(containsString("Test1")));
    }

    @Test
    public void create() throws Exception {

        User user = User.builder()
                .email("test@test.com")
                .name("Test1")
                .level(1L)
                .build();

        given(userService.addUser(any())).willReturn(user);

        mvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"name\":\"Test1\",\"level\":1}"))
                .andExpect(status().isCreated());

        verify(userService).addUser(any());
    }

    @Test
    public void update() throws Exception {

        User user = User.builder()
                .id(1L)
                .email("test2@test.com")
                .name("Test1")
                .level(3L)
                .build();

        given(userService.updateUser(eq(1L), any())).willReturn(user);


        mvc.perform(patch("/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":1,\"email\":\"test2@test.com\",\"name\":\"Test1\",\"level\":3}"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test2@test.com")))
                .andExpect(content().string(containsString("Test1")));

        verify(userService).updateUser(eq(1L), any());
    }

    @Test
    public void deactivate() throws Exception {
        mvc.perform(delete("/user/1"))
                .andExpect(status().isOk());

        verify(userService).deactivateUser(eq(1L));
    }
}