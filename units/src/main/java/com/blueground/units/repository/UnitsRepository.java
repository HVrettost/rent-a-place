package com.blueground.units.repository;

import com.blueground.units.model.entity.Unit;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UnitsRepository extends PagingAndSortingRepository<Unit, UUID> {

    List<Unit> getUnitsByRegion(String region);
}
