package com.blueground.units.repository;

import com.blueground.units.model.entity.Unit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UnitsRepository extends CrudRepository<Unit, UUID> {
}
