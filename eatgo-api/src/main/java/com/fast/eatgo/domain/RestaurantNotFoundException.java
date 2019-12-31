package com.fast.eatgo.domain;

public class RestaurantNotFoundException extends RuntimeException {

    public RestaurantNotFoundException (Long id) {
        super("Not Found : " + id);
    }
}
