package org.mrstm.uberreviewservice;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mrstm.uberreviewservice.adaptors.CreateReviewDTO_to_Review;
import org.mrstm.uberreviewservice.controllers.ReviewController;
import org.mrstm.uberreviewservice.models.Review;
import org.mrstm.uberreviewservice.services.BookingReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class ReviewControllerTest {
    @InjectMocks //for mocking service layer as controller is dependent on it.
    private ReviewController reviewController;

    @Mock //specifying which layer is being mocked.
    private BookingReviewService reviewService;

    @Mock
     private CreateReviewDTO_to_Review createReviewDTOToReview;


    @BeforeEach //if there are 20 tests this line is going to be executed 20 times before each test is executed.
    //Initializes the mocks before each test case.
    public void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findReviewById_Success(){
        long reviewId = 1L;
        Review mockReview = Review.builder().build();
        mockReview.setId(reviewId);

        //mocking
        when(reviewService.getReviewById(reviewId)).thenReturn(Optional.of(mockReview));

        //performing test
        ResponseEntity<?> response = reviewController.getReviewById(reviewId);

        //assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Optional<Review> returnedReview = (Optional<Review>) response.getBody();
        assertEquals(reviewId , returnedReview.get().getId());

    }
}
