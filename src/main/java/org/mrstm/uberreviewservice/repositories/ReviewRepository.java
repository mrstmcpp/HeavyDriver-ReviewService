package org.mrstm.uberreviewservice.repositories;

import org.mrstm.uberentityservice.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review , Long> {

}
