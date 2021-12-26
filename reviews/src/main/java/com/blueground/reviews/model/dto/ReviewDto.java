package com.blueground.reviews.model.dto;

import lombok.Getter;
import lombok.NonNull;
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
