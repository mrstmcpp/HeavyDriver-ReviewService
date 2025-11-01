package org.mrstm.uberreviewservice.dtos;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class CreateReviewDTO {
    private String content;
    private Double rating;
    @NonNull
    private Long bookingId;
}
