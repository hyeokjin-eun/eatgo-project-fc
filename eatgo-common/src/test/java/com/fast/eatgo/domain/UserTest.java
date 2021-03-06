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
    public void restaurantOwner() {
        User user = User.builder()
                .level(1L).build();

        assertThat(user.isRestaurantOwner(), is(false));

        user.setRestaurantId(1L);

        assertThat(user.isRestaurantOwner(), is(true));
        assertThat(user.getRestaurantId(), is(1L));
    }
}