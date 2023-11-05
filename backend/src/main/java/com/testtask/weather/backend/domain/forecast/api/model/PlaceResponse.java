package com.testtask.weather.backend.domain.forecast.api.model;

import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Builder
@Jacksonized
public record PlaceResponse(
        UUID id,
        String name,
        String phenomenon,
        int tempmin,
        int tempmax
) {
}
