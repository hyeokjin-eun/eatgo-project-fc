package com.fast.eatgo.inter;

import com.fast.eatgo.application.RestaurantService;
import com.fast.eatgo.domain.Restaurant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("")
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("{id}")
    public Restaurant detail(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
        String name = resource.getName();
        String address = resource.getAddress();

        Restaurant restaurant = Restaurant.builder()
                .name(name)
                .address(address)
                .build();

        restaurantService.addRestaurant(restaurant);

        URI uri = new URI("/restaurant/" + restaurant.getId());
        return ResponseEntity.created(uri).body("{}");
    }
}

