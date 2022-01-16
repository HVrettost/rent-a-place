package com.rentaplace.units.model.dto;

import java.math.BigDecimal;

public record UnitRequestDto(String imageUrl,
                             String title,
                             String region,
                             String description,
                             Boolean cancellationPolicy,
                             BigDecimal price) {
}
