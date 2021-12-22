package com.blueground.units.model.dto;

import lombok.*;

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
