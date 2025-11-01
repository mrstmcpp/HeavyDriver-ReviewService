package org.mrstm.uberreviewservice.repositories;

import org.mrstm.uberentityservice.dto.passenger.PassengerBookingDTO;
import org.mrstm.uberentityservice.dto.review.PublishReviewResponseDto;
import org.mrstm.uberentityservice.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("""
                SELECT new org.mrstm.uberentityservice.dto.review.PublishReviewResponseDto(
                    CAST(r.id AS string),
                    r.content,
                    CAST(b.id AS string),
                    r.rating
                )
                FROM Review r
                JOIN BookingReview br ON br.id = r.id
                JOIN Booking b ON br.booking = b
                WHERE b.passenger.id = :userId
                ORDER BY r.createdAt DESC
            """)
    Page<PublishReviewResponseDto> findAllReviewByUserId(
            @Param("userId") Long userId,
            Pageable pageable
    );


    @Query("""
    SELECT new org.mrstm.uberentityservice.dto.review.PublishReviewResponseDto(
        CAST(r.id AS string),
        r.content,
        CAST(b.id AS string),
        r.rating
    )
    FROM Review r
    JOIN BookingReview br ON br.id = r.id
    JOIN Booking b ON br.booking.id = b.id
    WHERE r.id = :reviewId
""")
    Optional<PublishReviewResponseDto> findReviewById(@Param("reviewId") Long reviewId);


}


