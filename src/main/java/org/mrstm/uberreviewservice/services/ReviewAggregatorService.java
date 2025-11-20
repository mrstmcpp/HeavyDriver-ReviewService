package org.mrstm.uberreviewservice.services;

import lombok.RequiredArgsConstructor;
import org.mrstm.uberreviewservice.repositories.ReviewRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewAggregatorService {
    private final ReviewRepository reviewRepository;
    public void calculateAvgReviewForAllDrivers(){
//        System.out.println("CRON : Updating avg reviews ." + new Date());
        reviewRepository.getAverageOfAllReviewByDriverId();
//        System.out.println("CRON : Updation done." + new Date());
    }
}
