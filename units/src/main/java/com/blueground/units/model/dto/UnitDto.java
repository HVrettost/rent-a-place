package com.blueground.units.model.dto;

import org.springframework.lang.NonNull;

import java.math.BigDecimal;
import java.util.UUID;

public record UnitDto(@NonNull UUID unitId,
                      @NonNull String imageUrl,
                      @NonNull String title,
                      @NonNull String region,
                      @NonNull String description,
                      @NonNull Boolean cancellationPolicy,
                      @NonNull BigDecimal price,
                      @NonNull Integer averageScore) {
}
