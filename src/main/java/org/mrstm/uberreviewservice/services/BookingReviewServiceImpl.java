package org.mrstm.uberreviewservice.services;


import jakarta.persistence.EntityNotFoundException;
import org.hibernate.FetchNotFoundException;
import org.mrstm.uberentityservice.models.Review;
import org.mrstm.uberreviewservice.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingReviewServiceImpl implements BookingReviewService {
    private final ReviewRepository reviewRepository;
    public BookingReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public Review publishReview(Review review) {
        return reviewRepository.save(review);
    }


    @Override
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    public Optional<Review> getReviewById(Long id) {
        Optional<Review> review = reviewRepository.findById(id);
        try {

            if(review.isEmpty()) {
                throw new EntityNotFoundException("Review with id " + id + " not found");
            }
            return review;

        } catch (Exception e) {
            throw new FetchNotFoundException("Unable to fetch, PLease try again later!", id);
        }
    }

    @Override
    public Review updateReview(Long id, Review newReview) {
        Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        try {
            if(newReview.getRating() != null){
                review.setRating(newReview.getRating());
            }
            if(newReview.getContent() != null){
                review.setContent(newReview.getContent());
            }
            return reviewRepository.save(review);
        }catch (Exception e) {
            throw new FetchNotFoundException("Unable to fetch, please try again later!", id);
        }
    }

    @Override
    public boolean deleteReview(Long id) {
        try{
            Review review = reviewRepository.findById(id).orElseThrow(EntityNotFoundException::new);
            reviewRepository.delete(review);
            return true;
        }catch (Exception e) {
            return false;
        }
    }
}
