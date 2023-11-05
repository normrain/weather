package com.testtask.weather.backend.domain.forecast.api.model;

import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Builder
@Jacksonized
public record ForecastResponse(
        UUID id,
        ForecastDaytimeResponse day,
        ForecastDaytimeResponse night,
        Date date
) {
}
