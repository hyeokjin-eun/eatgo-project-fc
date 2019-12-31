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
    public List<Restaurant> list() {
        return restaurantService.getRestaurants();
    }

    @GetMapping("{id}")
    public Restaurant detail(@PathVariable Long id) {
        return restaurantService.getRestaurant(id);
    }

    @PostMapping("")
    public ResponseEntity<?> create(@RequestBody Restaurant resource) throws URISyntaxException {
        Restaurant restaurant = Restaurant.builder()
                .name(resource.getName())
                .address(resource.getAddress())
                .build();

        restaurantService.addRestaurant(restaurant);

        URI uri = new URI("/restaurant/" + restaurant.getId());
        return ResponseEntity.created(uri).body("{}");
    }

    @PatchMapping("{id}")
    public String update(@PathVariable Long id, @RequestBody Restaurant resource){
        String name = resource.getName();
        String address = resource.getAddress();
        restaurantService.updateRestaurant(id, name, address);
        return "{}";
    }
}

