package com.rentaplace.test.repository;

import com.rentaplace.units.model.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnitsTestRepository extends JpaRepository<Unit, UUID> {

    @Modifying
    @Query(value = "UPDATE RENTAPLACE.UNITS SET SEARCH_TOKENS = TO_TSVECTOR(?1) WHERE UNIT_ID = ?2", nativeQuery = true)
    void updateVectorsForRegionAndTitle(String vectorValue, UUID unitId);
}
