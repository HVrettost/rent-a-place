package com.blueground.units.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnitsResponseDto {

    private List<UnitDto> units;
    private Long totalUnits;
    private Integer totalPages;
}
