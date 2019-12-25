package com.fast.eatgo.inter;

import com.fast.eatgo.domain.Restaurant;
import com.fast.eatgo.domain.RestaurantRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/restaurant")
public class RestaurantController {

    @Autowired
    public RestaurantRepository restaurantRepository;

    @GetMapping("")
    public List<Restaurant> list() {
        return restaurantRepository.findAll();
    }

    @GetMapping("{id}")
    public Restaurant detail(@PathVariable Long id) {
        return restaurantRepository.findById(id);
    }
}
