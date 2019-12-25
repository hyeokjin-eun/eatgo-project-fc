package com.fast.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImpl implements RestaurantRepository {

    private List<Restaurant> restaurants = new ArrayList<>();

    private RestaurantRepositoryImpl() {
        restaurants.add(Restaurant.builder().id(1004L).name("Bob zip").address("Seoul").build());
        restaurants.add(Restaurant.builder().id(2020L).name("pizza zip").address("Seoul").build());
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(restaurant -> restaurant.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
