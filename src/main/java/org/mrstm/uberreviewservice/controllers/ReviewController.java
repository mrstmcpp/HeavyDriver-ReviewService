package org.mrstm.uberreviewservice.controllers;


import org.mrstm.uberentityservice.dto.review.PublishReviewRequestDto;
import org.mrstm.uberentityservice.dto.review.PublishReviewResponseDto;
import org.mrstm.uberentityservice.dto.review.ReviewByUserResponseDto;
import org.mrstm.uberreviewservice.services.BookingReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("")
public class ReviewController {
    private final BookingReviewService bookingReviewService;

    public ReviewController(BookingReviewService bookingReviewService) {
        this.bookingReviewService = bookingReviewService;
    }

    @GetMapping("")
    public ResponseEntity<ReviewByUserResponseDto> getReviewByPassenger(@RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                                                                        @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize, @RequestHeader("X-User-Id") Long userId,
                                                                        @RequestHeader("X-User-Role") String role) {

        return new ResponseEntity<>(bookingReviewService.getReviewByPassenger(pageSize , offset ,userId), HttpStatus.OK);
    }

    @PostMapping("/booking/{bookingId}")
    public ResponseEntity<PublishReviewResponseDto> addReview(@Validated @RequestBody PublishReviewRequestDto publishReviewRequestDto, @PathVariable String bookingId, @RequestHeader("X-User-Id") Long userId,
                                                              @RequestHeader("X-User-Role") String role) {
        return new ResponseEntity<>(bookingReviewService.publishReview(publishReviewRequestDto, Long.parseLong(bookingId), userId), HttpStatus.CREATED);
    }

//    @DeleteMapping("/{id}")
//    public String deleteReview(@PathVariable Long id) {
//        if (bookingReviewService.deleteReview(id)) {
//            return "Review deleted successfully";
//        } else {
//            return "Review could not be deleted";
//        }
//    }
//
//    @PutMapping("/{id}")
//    public Review updateReview(@PathVariable Long id, @RequestBody Review review) {
//        return bookingReviewService.updateReview(id, review);
//    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<PublishReviewResponseDto> getReviewById(@PathVariable Long reviewId) {
            return new ResponseEntity<>(bookingReviewService.getReviewById(reviewId), HttpStatus.OK);
    }

    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<PublishReviewResponseDto> getReviewByBookingId(@PathVariable String bookingId, @RequestHeader("X-User-Id") Long userId,
                                                                         @RequestHeader("X-User-Role") String role) {
        return new ResponseEntity<>(bookingReviewService.getReviewByBookingId(Long.parseLong(bookingId)), HttpStatus.OK);
    }



}
