package com.fast.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void creation() {
        User user = User.builder()
                .email("test@test.com")
                .name("Test1")
                .level(1L)
                .build();

        assertThat(user.getName(), is("Test1"));
        assertThat(user.getEmail(), is("test@test.com"));
        assertThat(user.isAdmin(), is(false));
        assertThat(user.isActive(), is(true));

        user.deActive();

        assertThat(user.isActive(), is(false));
    }

    @Test
    public void accessTokenWithPassword() {
        User user = User.builder()
                .password("ACCESSTOKEN")
                .build();

        assertThat(user.getAccessToken(), is("ACCESSTOKE"));
    }

    @Test
    public void accessTokenWithOutPassword() {
        User user = new User();

        assertThat(user.getAccessToken(), is(""));
    }
}