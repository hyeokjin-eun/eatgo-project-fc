package com.fast.eatgo.inter;

import com.fast.eatgo.application.UserService;
import com.fast.eatgo.domain.EmailNotExistedException;
import com.fast.eatgo.domain.PasswordWrongException;
import com.fast.eatgo.domain.User;
import com.fast.eatgo.util.JwtUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(SessionController.class)
public class SessionControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private JwtUtil jwtUtil;

    @MockBean
    private UserService userService;

    @Test
    public void createWithValidAttributes() throws Exception {

        String email = "test@test.com";
        String password = "test";

        User mockUser = User.builder()
                .id(1L)
                .name("Test1")
                .password("ACCESSTOKEN")
                .build();

        given(userService.authenticate(email, password)).willReturn(mockUser);

        given(jwtUtil.createToken(1L, "Test1")).willReturn("header.payload.signature");

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/session"))
                .andExpect(content().string(containsString("{\"accessToken\":\"header.payload.signature\"}")))
                .andExpect(content().string(containsString(".")));

        verify(userService).authenticate(eq("test@test.com"), eq("test"));
    }

    @Test
    public void createWithNotExistedEmail() throws Exception {
        given(userService.authenticate(eq("x@test.com"), eq("test"))).willThrow(EmailNotExistedException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"x@test.com\",\"password\":\"test\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("x@test.com"), eq("test"));
    }

    @Test
    public void createWithInvalidAttributes() throws Exception {
        given(userService.authenticate(eq("test@test.com"), eq("x"))).willThrow(PasswordWrongException.class);

        mvc.perform(post("/session")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"email\":\"test@test.com\",\"password\":\"x\"}"))
                .andExpect(status().isBadRequest());

        verify(userService).authenticate(eq("test@test.com"), eq("x"));
    }
}