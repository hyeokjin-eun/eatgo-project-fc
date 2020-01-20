package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReviewService;
import com.fast.eatgo.domain.Review;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/restaurant/{restaurantId}/review")
    public ResponseEntity<?> create(Authentication authentication, @PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {
        Claims claims = (Claims) authentication.getPrincipal();
        Review review = reviewService.addReview(restaurantId, claims.get("name", String.class), resource.getScore(), resource.getDescription());
        return ResponseEntity.created(new URI("/restaurant/" + restaurantId + "/review/" + review.getId()))
                .body("{}");
    }
}
