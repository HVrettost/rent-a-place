package com.blueground.units.repository;

import com.blueground.units.model.entity.Unit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnitsRepository extends JpaRepository<Unit, UUID> {

    Page<Unit> getUnitsByRegion(String region, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE MARSRENTAL.UNITS SET AVERAGE_SCORE = ?1 WHERE UNIT_ID = ?2", nativeQuery = true)
    void updateUnitAverageScore(Integer score, UUID unitId);
}
