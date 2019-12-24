package com.fast.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantTests {

    @Test
    public void creation () {
        Restaurant restaurant = new Restaurant("Bob zip");

        assertThat(restaurant.getName(), is("Bob zip"));
    }

    @Test
    public void information() {
        Restaurant restaurant = new Restaurant("Bob zip", "Seoul");

        assertThat(restaurant.getInformation(), is("Bob zip In Seoul"));
    }

}