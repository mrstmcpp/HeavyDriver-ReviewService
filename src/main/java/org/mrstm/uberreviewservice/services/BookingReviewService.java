package org.mrstm.uberreviewservice.services;


import org.mrstm.uberentityservice.dto.review.PublishReviewRequestDto;
import org.mrstm.uberentityservice.dto.review.PublishReviewResponseDto;
import org.mrstm.uberentityservice.dto.review.ReviewByUserResponseDto;
import org.mrstm.uberentityservice.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingReviewService {
    public PublishReviewResponseDto publishReview(PublishReviewRequestDto publishReviewRequestDto , Long bookingId , Long userId);

    public ReviewByUserResponseDto getReviewByPassenger(Integer pageSize, Integer offset, Long userId);

    public PublishReviewResponseDto getReviewById(Long reviewId);

    public PublishReviewResponseDto getReviewByBookingId(Long bookingId);

    public Review updateReview(Long id , Review review);

    public boolean deleteReview(Long id);
}
