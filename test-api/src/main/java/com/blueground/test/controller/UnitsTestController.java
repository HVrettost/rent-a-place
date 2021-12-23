package com.blueground.test.controller;

import com.blueground.test.api.UnitsTestApi;
import com.blueground.test.dto.UnitCreationRequestDto;
import com.blueground.test.repository.UnitsTestRepository;
import com.blueground.units.model.entity.Unit;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class UnitsTestController implements UnitsTestApi {

    private final UnitsTestRepository unitsTestRepository;

    @Override
    public ResponseEntity<Void> deleteAllUnits() {
        unitsTestRepository.deleteAll();

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    @Transactional
    public ResponseEntity<Unit> createNewUnit(UnitCreationRequestDto dto) {
        Unit unit = new Unit();
        unit.setCancellationPolicy(Boolean.FALSE);
        unit.setDescription("a description");
        unit.setPrice(dto.getPrice());
        unit.setRegion(dto.getRegion());
        unit.setTitle(dto.getTitle());
        unit.setImageUrl("/image/url");

        Unit response = unitsTestRepository.save(unit);
        String stringForVectorization = dto.getRegion() + " " + dto.getTitle();
        unitsTestRepository.updateVectorsForRegionAndTitle(stringForVectorization, response.getUnitId());

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> getUnitScore(String unitId) {
        Integer averageScore = unitsTestRepository.getById(UUID.fromString(unitId)).getAverageScore();

        return new ResponseEntity<>(averageScore, HttpStatus.OK);
    }
}
