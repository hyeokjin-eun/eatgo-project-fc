package com.fast.eatgo.inter;

import com.fast.eatgo.application.RestaurantService;
import com.fast.eatgo.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping("")
    public List<Restaurant> list(@RequestParam("region")  String region, @RequestParam("category") Long categoryId) {
        return restaurantService.getRestaurants(region, categoryId);
    }

    @GetMapping("{id}")
    public Restaurant detail(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }
}

