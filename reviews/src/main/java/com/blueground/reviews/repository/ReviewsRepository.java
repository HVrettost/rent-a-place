package com.blueground.reviews.repository;

import com.blueground.reviews.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewsRepository extends JpaRepository<Review, UUID> {

    @Query(value = "SELECT AVG(SCORE) FROM MARSRENTAL.REVIEWS WHERE UNIT_ID = ?1", nativeQuery = true)
    Optional<Float> calculateUnitAverageScore(UUID unitId);
}
