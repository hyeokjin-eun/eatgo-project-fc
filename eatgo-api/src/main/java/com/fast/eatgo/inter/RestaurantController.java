package com.fast.eatgo.inter;

import com.fast.eatgo.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @GetMapping("")
    public List<Restaurant> list() {
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = Restaurant
                .builder()
                .id(1004L)
                .name("Bob zip")
                .address("Seoul")
                .build();

        log.info("restaurant : {}" ,restaurant);
        restaurants.add(restaurant);

        return restaurants;
    }
}
