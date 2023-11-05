package com.testtask.weather.backend.domain.forecast.api.model;


import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
public record ForecastsDTO(
        List<ForecastDTO> forecast
) {
}
