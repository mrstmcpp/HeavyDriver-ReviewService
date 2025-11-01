package org.mrstm.uberreviewservice.adaptors;

import org.mrstm.uberreviewservice.dtos.CreateReviewDTO;
import org.mrstm.uberentityservice.models.Review;

public interface CreateReviewDTO_to_Review {
    public Review convertToReview(CreateReviewDTO createReviewDTO);
}
