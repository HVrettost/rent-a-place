package com.blueground.reviews.repository;

import com.blueground.reviews.model.entity.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReviewsRepository extends CrudRepository<Review, UUID> {
}
