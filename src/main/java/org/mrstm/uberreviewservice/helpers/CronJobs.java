package org.mrstm.uberreviewservice.helpers;

import lombok.RequiredArgsConstructor;
import org.mrstm.uberreviewservice.services.ReviewAggregatorService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CronJobs {
    private final ReviewAggregatorService reviewAggregatorService;

    @Scheduled(cron = "0 00 2 * * *")
    public void execute(){
        reviewAggregatorService.calculateAvgReviewForAllDrivers();
    }

}
