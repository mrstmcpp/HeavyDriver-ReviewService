package org.mrstm.uberreviewservice.controllers;


import org.mrstm.uberreviewservice.adaptors.CreateReviewDTO_to_ReviewImpl;
import org.mrstm.uberreviewservice.dtos.CreateReviewDTO;
import org.mrstm.uberentityservice.models.Review;
import org.mrstm.uberreviewservice.helpers.AuthPassengerDetails;
import org.mrstm.uberreviewservice.helpers.CustomUserPrincipal;
import org.mrstm.uberreviewservice.services.BookingReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/review")
public class ReviewController {
    private final BookingReviewService reviewService;
    private final CreateReviewDTO_to_ReviewImpl createReviewDTO_to_Review;
    public ReviewController(BookingReviewService reviewService , CreateReviewDTO_to_ReviewImpl createReviewDTO_to_Review) {
        this.createReviewDTO_to_Review = createReviewDTO_to_Review;
        this.reviewService = reviewService;
    }

    @GetMapping("")
    public List<Review> getReviews(@AuthenticationPrincipal CustomUserPrincipal user) {
//        AuthPassengerDetails u = (AuthPassengerDetails) user.getPrincipal();
        System.out.println("Passenger ID: " + user.getId());

        return reviewService.getAllReviews();
    }

    @PostMapping("")
    public ResponseEntity<?> addReview(@Validated @RequestBody CreateReviewDTO review, Authentication authentication) {
        Long passengerId = (Long) authentication.getPrincipal();
        System.out.println("passenger id : " + passengerId);
        Review r = createReviewDTO_to_Review.convertToReview(review);
        reviewService.publishReview(r);
        return new ResponseEntity<>(r , HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public String deleteReview(@PathVariable Long id) {
        if(reviewService.deleteReview(id)) {
            return "Review deleted successfully";
        }
        else {
            return "Review could not be deleted";
        }
    }

    @PutMapping("/{id}")
    public Review updateReview(@PathVariable Long id , @RequestBody Review review) {
        return reviewService.updateReview(id, review);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReviewById(@PathVariable Long id) {
        try{
            Optional<Review> review = reviewService.getReviewById(id);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>("Review could not be found", HttpStatus.NOT_FOUND);
        }
    }


}
