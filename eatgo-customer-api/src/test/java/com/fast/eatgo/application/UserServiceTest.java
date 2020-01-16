package com.fast.eatgo.application;

import com.fast.eatgo.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        userService = new UserService(userRepository, passwordEncoder);
    }

    @Test
    public void registerUser() {
        User mockUser = User.builder()
                .id(1L)
                .email("test@test.com")
                .name("Test1")
                .password("test")
                .level(1L)
                .build();

        given(userRepository.save(any())).willReturn(mockUser);

        User user = userService.registerUser(User.builder()
                .email("test@test.com")
                .name("Test1")
                .password("test")
                .build());

        verify(userRepository).save(any());

        assertThat(user.getId(), is(1L));
        assertThat(user.getEmail(), is("test@test.com"));
        assertThat(user.getName(), is("Test1"));
        assertThat(user.getPassword(), is("test"));
        assertThat(user.getLevel(), is(1L));
    }


    @Test(expected = UserExistedException.class)
    public void registerUserWithExistedEmail() {

        User mockUser = User.builder()
                .id(1L)
                .email("test@test.com")
                .name("Test1")
                .password("test")
                .level(1L)
                .build();

        given(userRepository.findByEmail(any())).willReturn(Optional.ofNullable(mockUser));

        User user = userService.registerUser(User.builder()
                .email("test@test.com")
                .name("Test1")
                .password("test")
                .build());

        verify(userRepository, never()).save(any());
    }

    @Test
    public void authenticateWithValidAttributes() {
        String email = "test@test.com";
        String password = "test";

        User mockUser = User.builder()
                .email(email)
                .build();
        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(false);

        User user = userService.authenticate(email, password);

        assertThat(user.getEmail(), is(email));
    }

    @Test(expected = EmailNotExistedException.class)
    public void authenticateWithNotExistedEmail() {
        String email = "x@test.com";
        String password = "test";

        given(userRepository.findByEmail(email)).willReturn(Optional.empty());

        User user = userService.authenticate(email, password);

        assertThat(user.getEmail(), is(email));
    }

    @Test(expected = PasswordWrongException.class)
    public void authenticateWithWrongPassword() {
        String email = "test@test.com";
        String password = "x";

        User mockUser = User.builder()
                .email(email)
                .password(password)
                .build();

        given(userRepository.findByEmail(email)).willReturn(Optional.ofNullable(mockUser));

        given(passwordEncoder.matches(any(), any())).willReturn(true);

        User user = userService.authenticate("test@test.com", "test");
    }
}