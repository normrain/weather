package com.testtask.weather.backend.domain.forecast.api.model.dtos;

import lombok.Builder;

@Builder
public record PlaceDTO(
        String name,
        String phenomenon,
        int tempmin,
        int tempmax
) {
}
