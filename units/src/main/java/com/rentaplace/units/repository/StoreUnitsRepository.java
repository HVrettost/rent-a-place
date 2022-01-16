package com.rentaplace.units.repository;

import com.rentaplace.units.model.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface StoreUnitsRepository extends JpaRepository<Unit, UUID> {
}
