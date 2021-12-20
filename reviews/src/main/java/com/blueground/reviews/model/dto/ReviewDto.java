package com.blueground.reviews.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReviewDto {

    private UUID reviewId;
    private Integer score;
    private String comment;
    private UUID unitId;
    private UUID userId;
}
