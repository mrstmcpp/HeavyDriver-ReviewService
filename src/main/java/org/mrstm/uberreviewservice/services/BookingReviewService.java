package org.mrstm.uberreviewservice.services;


import org.mrstm.uberentityservice.models.Review;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookingReviewService {
    public Review publishReview(Review review);

    public List<Review> getAllReviews();

    public Optional<Review> getReviewById(Long id);

    public Review updateReview(Long id , Review review);

    public boolean deleteReview(Long id);
}
