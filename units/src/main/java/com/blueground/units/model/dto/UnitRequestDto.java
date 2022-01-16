package com.blueground.units.model.dto;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;

public record UnitRequestDto(String imageUrl,
                             String title,
                             String region,
                             String description,
                             Boolean cancellationPolicy,
                             BigDecimal price) {
}
