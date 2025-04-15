package org.mrstm.uberreviewservice.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;



@Entity
@Getter
@Setter
@Builder //implements builder design pattern
@AllArgsConstructor //create constructor with all arguments
@NoArgsConstructor
//@EntityListeners(AuditingEntityListener.class) //is a JPA annotation that lets you hook into the lifecycle of an entity—things like when it's created, updated, or deleted—by registering a listener class to respond to those events.
//also need to add @EnableJpaAuditing in entry point of application...
//alternative is to pass date in builder class.
//@Table(name = "ReviewData") //we can give custom name to our table like this.
@Table(name = "booking_review")
@Inheritance(strategy = InheritanceType.JOINED)

/*
table per class creates tables for each class, such as different table
for passagner review , driver review as well as bookingreview which is this file
**and alll tables contains id , content , rating , createdat , updatedat..
which shows data redundancy.
 */

/*
Joined -> this will also create 3 tables for us....
bt all three tables will have different content except primary id of the content
no data redundancy...
 */
public class Review extends BaseModel {

    @Column(nullable = false)
    private String content;

    private Double rating;


    @OneToOne(cascade = {CascadeType.ALL}) //one booking one review
    //cascade type to prevent error related to one object should be in db to create another.
    //ie booking isn't being created without review object prepared in db
    private Booking booking;
}
