package org.mrstm.uberreviewservice.services;


import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.NotFoundException;
import org.hibernate.FetchNotFoundException;
import org.mrstm.uberentityservice.dto.review.PublishReviewRequestDto;
import org.mrstm.uberentityservice.dto.review.PublishReviewResponseDto;
import org.mrstm.uberentityservice.dto.review.ReviewByUserResponseDto;
import org.mrstm.uberentityservice.models.Booking;
import org.mrstm.uberentityservice.models.BookingReview;
import org.mrstm.uberentityservice.models.Review;
import org.mrstm.uberreviewservice.repositories.BookingRepository;
import org.mrstm.uberreviewservice.repositories.ReviewRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingReviewServiceImpl implements BookingReviewService {
    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;

    public BookingReviewServiceImpl(ReviewRepository reviewRepository, BookingRepository bookingRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
    }


    @Override
    @Transactional
    public PublishReviewResponseDto publishReview(PublishReviewRequestDto publishReviewRequestDto, Long bookingId, Long userId) {
        //todo: verify user belongs to this booking.

        Booking booking = bookingRepository.getBookingById(bookingId).orElseThrow(() -> new NotFoundException("No booking found."));
        if (booking.getBookingReview() != null) {
            throw new BadRequestException("Review already published for this booking.");
        }

        if (!booking.getPassenger().getId().equals(userId)) {
            throw new BadRequestException("This booking is not belongs to this passenger.");
        }

        BookingReview review = BookingReview.builder()
                .content(publishReviewRequestDto.getContent())
                .rating(publishReviewRequestDto.getRating())
                .booking(booking)
                .build();
        reviewRepository.save(review);
        return PublishReviewResponseDto.builder()
                .reviewId(review.getId().toString())
                .bookingId(review.getBooking().getId().toString())
                .content(review.getContent())
                .rating(review.getRating())
                .build();

    }


    @Override
    public ReviewByUserResponseDto getReviewByPassenger(Integer pageSize, Integer offset, Long userId) {
        int defaultPage = 0;
        int defaultSize = 10;

        int page = (offset != null && offset >= 0) ? offset : defaultPage;
        int size = (pageSize != null && pageSize > 0) ? pageSize : defaultSize;

        Pageable pageable = PageRequest.of(page, size);
        Page<PublishReviewResponseDto> reviewPage = reviewRepository.findAllReviewByUserId(userId, pageable);

        return ReviewByUserResponseDto.builder()
                .reviewList(reviewPage.getContent())
                .currentPage(reviewPage.getNumber())
                .totalItems(reviewPage.getTotalElements())
                .totalPages(reviewPage.getTotalPages())
                .build();
    }


    @Override
    public PublishReviewResponseDto getReviewById(Long reviewId) {
        return reviewRepository.findReviewById(reviewId).orElseThrow(() -> new NotFoundException("No review found with provided review ID."));
    }

    @Override
    public Review updateReview(Long id, Review newReview) {
        Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        try {
            if (newReview.getRating() != null) {
                review.setRating(newReview.getRating());
            }
            if (newReview.getContent() != null) {
                review.setContent(newReview.getContent());
            }
            return reviewRepository.save(review);
        } catch (Exception e) {
            throw new FetchNotFoundException("Unable to fetch, please try again later!", id);
        }
    }

    @Override
    public boolean deleteReview(Long id) {
        try {
            Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            reviewRepository.delete(review);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
