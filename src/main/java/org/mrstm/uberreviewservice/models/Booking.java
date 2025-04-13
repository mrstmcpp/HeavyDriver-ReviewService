package org.mrstm.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking extends BaseModel {

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}) //one booking one review
    //cascade type to prevent error related to one object should be in db to create another.
    //ie booking isn't being created without review object prepared in db
    private Review review;


    @Enumerated(value = EnumType.STRING) //tells spring that it is an enum.
    private BookingStatus bookingStatus;

    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    private Long totalDistance;

    @ManyToOne
    private Driver driver;

    @ManyToOne(fetch = FetchType.LAZY)
    private Passenger passenger;
}
