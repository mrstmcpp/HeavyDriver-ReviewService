package org.mrstm.uberreviewservice.repositories;

import org.mrstm.uberreviewservice.models.Booking;
import org.mrstm.uberreviewservice.models.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findAllByDriverIn(List<Driver> drivers);
    //to make it working transactional must be annotated.
}
