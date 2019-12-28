package com.fast.eatgo.domain;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RestaurantRepositoryImplTest {

    @Test
    public void save() {
        RestaurantRepository restaurantRepository = new RestaurantRepositoryImpl();

        int oldCount = restaurantRepository.findAll().size();

        Restaurant restaurant = Restaurant.builder()
                .name("BeRyong")
                .address("Bosan")
                .build();

        restaurantRepository.save(restaurant);

        assertThat(restaurant.getId(), is(1234L));

        int newCount = restaurantRepository.findAll().size();

        assertThat((newCount - oldCount), is(1));
    }
}