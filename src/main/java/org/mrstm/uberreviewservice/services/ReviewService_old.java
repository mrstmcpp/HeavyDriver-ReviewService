package org.mrstm.uberreviewservice.services;

import jakarta.transaction.Transactional;
import org.mrstm.uberreviewservice.models.Booking;
import org.mrstm.uberreviewservice.models.Driver;
import org.mrstm.uberreviewservice.repositories.BookingRepository;
import org.mrstm.uberreviewservice.repositories.DriverRepository;
import org.mrstm.uberreviewservice.repositories.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ReviewService_old implements CommandLineRunner {

    private final ReviewRepository reviewRepository;
    private final BookingRepository bookingRepository;
    private final DriverRepository driverRepository;

    public ReviewService_old(ReviewRepository reviewRepository, BookingRepository bookingRepository, DriverRepository driverRepository) {
        this.reviewRepository = reviewRepository;
        this.bookingRepository = bookingRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    @Transactional // fetchMode.subselect wasn't working in non-atomic mode
    public void run(String... args) throws Exception {
        System.out.println("Hello World... Preparing DB queries...");
//        Review review = Review.builder()
//                .content("Good rider")
//                .rating(5.0)
////                .updatedAt(new Date())
////                .createdAt(new Date())
//                .build(); //code to create java object.
//
//        Booking booking = Booking.builder()
//                        .endTime(new Date())
//                                .review(review)
//                                        .build();
//
//        bookingRepository.save(booking);
//        reviewRepository.save(review); //this code executes db query...
        /*
        Questionn is which one is to be created first????
        currently putting booking before review..
        this give errors...
        as this object is created in java not in mysql.
        And if we remove .review from the booking it will work
        solution1: save review first then booking...
        solution2: add cascade mechanism in booking classs
         */

//        Optional<Driver> driver = driverRepository.hqlFindByIdAndLicense(2L,"UP123");



//        if(driver.isPresent()) {
//            System.out.println(driver.get().getFullName());
////            List<Booking> bookings = driver.get().getBookings();
////            for (Booking booking1 : bookings) {
////                System.out.println(booking1.getBookingStatus());
////            }
//        }
//        List<Review> reviews = reviewRepository.findAll();
//        for (Review r : reviews) {
//            System.out.println(r.getContent());
//        }

        /// solution : for solution of N+1 problem
        List<Long> driverIds = new ArrayList<>(Arrays.asList(1L , 2L , 3L , 4L , 5L , 6L ));

        List<Driver> drivers = driverRepository.findAllByIdIn(driverIds);

        List<Booking> bookings = bookingRepository.findAllByDriverIn(drivers); //to make it working transactional must be annotated.
//        for (Driver driver : drivers) {
//            List<Booking> bookings = driver.getBookings();
//            bookings.forEach(booking -> System.out.println(booking.getBookingStatus())); // or driver.getId()
//
//        }


    }
}
