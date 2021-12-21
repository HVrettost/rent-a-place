package com.blueground.reviews.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ReviewDto {

    private int score;
    private String comment;
    private UUID unitId;
    private UUID userId;
}
