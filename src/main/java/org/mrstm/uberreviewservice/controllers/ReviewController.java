package org.mrstm.uberreviewservice.controllers;


import org.mrstm.uberreviewservice.models.Review;
import org.mrstm.uberreviewservice.services.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {
    private ReviewService reviewService;
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public List<Review> getReviews() {
        return reviewService.getAllReviews();
    }

    @PostMapping("")
    public Review addReview(@RequestBody Review review) {
        return reviewService.publishReview(review);
    }

    @DeleteMapping("{id}")
    public String deleteReview(@PathVariable Long id) {
        if(reviewService.deleteReview(id)) {
            return "Review deleted successfully";
        }
        else {
            return "Review could not be deleted";
        }
    }

    @GetMapping("{id}")
    public Optional<Review> getReview(@PathVariable Long id) {
        return reviewService.getReviewById(id);
    }

    @PutMapping("{id}")
    public Review updateReview(@PathVariable Long id , @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }


}
