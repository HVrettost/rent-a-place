package com.blueground.reviews.api;

import com.blueground.reviews.exception.ReviewsException;
import com.blueground.reviews.model.dto.ReviewDto;
import com.blueground.units.exception.UnitsException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "marsrental/v1")
public interface ReviewsApi {

    @PreAuthorize("hasAuthority('INSERT_REVIEW')")
    @PostMapping(value = "reviews",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> saveReview(@RequestBody ReviewDto reviewDto) throws ReviewsException, UnitsException;
}
