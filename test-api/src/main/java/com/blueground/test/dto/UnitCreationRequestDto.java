package com.blueground.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class UnitCreationRequestDto {

    private String region;
    private String title;
    private BigDecimal price;

}
