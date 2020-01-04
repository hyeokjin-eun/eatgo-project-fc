package com.fast.eatgo.inter;

import com.fast.eatgo.application.ReviewService;
import com.fast.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public List<Review> list() {
        return reviewService.getReviews();
    }

    @PostMapping("/restaurant/{restaurantId}/review")
    public ResponseEntity<?> create(@PathVariable Long restaurantId, @Valid @RequestBody Review resource) throws URISyntaxException {
        Review review = reviewService.addReview(restaurantId, resource);
        return ResponseEntity.created(new URI("/restaurant/" + restaurantId + "/review/" + review.getId()))
                .body("{}");
    }
}
