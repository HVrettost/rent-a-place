package com.blueground.units.repository;

import com.blueground.units.model.entity.Unit;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnitsRepository extends PagingAndSortingRepository<Unit, UUID> {

    @Query(value = "SELECT * "
            + "FROM MARSRENTAL.UNITS "
            + "WHERE SEARCH_TOKENS @@ TO_TSQUERY(?1) "
            + "ORDER BY AVERAGE_SCORE DESC NULLS LAST", nativeQuery = true)
    Slice<Unit> findUnitsByVectorizedValues(String searchValue, Pageable pageable);

    @Modifying
    @Query(value = "UPDATE MARSRENTAL.UNITS SET AVERAGE_SCORE = ?1 WHERE UNIT_ID = ?2", nativeQuery = true)
    void updateUnitAverageScore(Integer score, UUID unitId);
}
