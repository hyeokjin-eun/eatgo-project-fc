package com.fast.eatgo.application;

import com.fast.eatgo.domain.User;
import com.fast.eatgo.domain.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        userService = new UserService(userRepository);
    }

    @Test
    public void getUsers() {
        List<User> mockUsers = new ArrayList<>();
        mockUsers.add(User.builder()
                .email("test@test.com")
                .name("Test1")
                .level(1L)
                .build());

        given(userRepository.findAll()).willReturn(mockUsers);

        List<User> users = userService.getUsers();

        User user = users.get(0);

        assertThat(user.getEmail(), is("test@test.com"));
        assertThat(user.getName(), is("Test1"));
    }

    @Test
    public void addUser() {
        User mockUser = User.builder()
                .email("test@test.com")
                .name("Test1")
                .level(1L)
                .build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.addUser(mockUser);

        verify(userRepository).save(mockUser);

        assertThat(user.getEmail(), is("test@test.com"));
        assertThat(user.getName(), is("Test1"));
    }

    @Test
    public void updateUser() {

        User mockUser = User.builder()
                .id(1L)
                .email("test2@test.com")
                .name("Test1")
                .level(3L)
                .build();

        given(userRepository.findById(eq(1L))).willReturn(Optional.of(User.builder()
                .id(1L)
                .email("test@test.com")
                .name("Test1")
                .level(1L)
                .build()));

        User user = userService.updateUser(1L, mockUser);

        verify(userRepository).findById(eq(1L));

        assertThat(user.getEmail(), is("test2@test.com"));
        assertThat(user.getName(), is("Test1"));
        //verify(userRepository).save(any());

    }
}