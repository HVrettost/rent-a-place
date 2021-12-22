package com.blueground.reviews.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "REVIEWS", schema = "MARSRENTAL")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REVIEW_ID")
    private UUID reviewId;

    @Column(name = "SCORE")
    private Integer score;

    @Column(name = "COMMENT")
    private String comment;

    @Column(name = "UNIT_ID")
    private UUID unitId;

    @Column(name = "USER_ID")
    private UUID userId;

}
