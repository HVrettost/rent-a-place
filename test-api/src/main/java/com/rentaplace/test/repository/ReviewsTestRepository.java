package com.rentaplace.test.repository;

import com.rentaplace.reviews.model.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReviewsTestRepository extends JpaRepository<Review, UUID> {

    @Query(value = "SELECT AVG(SCORE) FROM RENTAPLACE.REVIEWS WHERE UNIT_ID = ?1", nativeQuery = true)
    Optional<Float> calculateUnitAverageScore(UUID unitId);

    List<Review> getAllByUnitId(UUID unitId);
}
