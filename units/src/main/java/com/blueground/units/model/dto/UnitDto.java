package com.blueground.units.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
public class UnitDto {

    private UUID unitId;
    private String imageUrl;
    private String title;
    private String region;
    private String description;
    private Boolean cancellationPolicy;
    private BigDecimal price;
    private Integer averageScore;
}
