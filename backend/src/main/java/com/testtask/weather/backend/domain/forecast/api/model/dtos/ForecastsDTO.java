package com.testtask.weather.backend.domain.forecast.api.model.dtos;


import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastDTO;
import lombok.Builder;
import lombok.extern.jackson.Jacksonized;

import java.util.List;

@Jacksonized
@Builder
public record ForecastsDTO(
        List<ForecastDTO> forecast
) {
}
