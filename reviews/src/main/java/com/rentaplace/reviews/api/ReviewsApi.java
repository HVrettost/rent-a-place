package com.rentaplace.reviews.api;

import com.rentaplace.common.exception.RentAPlaceCoreException;
import com.rentaplace.reviews.model.dto.ReviewDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "rentaplace/v1")
public interface ReviewsApi {

    @PreAuthorize("hasAuthority('INSERT_REVIEW')")
    @PostMapping(value = "reviews",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<Void> saveReview(@RequestBody ReviewDto reviewDto) throws RentAPlaceCoreException;
}
