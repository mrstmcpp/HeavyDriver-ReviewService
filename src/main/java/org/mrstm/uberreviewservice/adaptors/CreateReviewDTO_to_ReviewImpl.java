package org.mrstm.uberreviewservice.adaptors;

import org.mrstm.uberentityservice.models.BookingReview;
import org.mrstm.uberreviewservice.dtos.CreateReviewDTO;
import org.mrstm.uberentityservice.models.Booking;
import org.mrstm.uberentityservice.models.Review;
import org.mrstm.uberreviewservice.repositories.BookingRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CreateReviewDTO_to_ReviewImpl implements CreateReviewDTO_to_Review {

    private BookingRepository bookingRepository;

    public CreateReviewDTO_to_ReviewImpl(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Review convertToReview(CreateReviewDTO createReviewDTO) {
        if(createReviewDTO.getBookingId() == null) {
            throw new IllegalArgumentException("Booking id cannot be null");
        }
        if(createReviewDTO.getContent() == null) {
            throw new IllegalArgumentException("Content cannot be null");
        }
        Optional<Booking> booking = bookingRepository.findById(createReviewDTO.getBookingId());
        if(booking.isEmpty()) {
            throw new IllegalArgumentException("Booking not found");
        }
        BookingReview review = new BookingReview();
        review.setBooking(booking.get());
        review.setRating(createReviewDTO.getRating());
        review.setContent(createReviewDTO.getContent());
        return review;

    }
}
