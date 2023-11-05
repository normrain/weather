package com.testtask.weather.backend.domain.forecast.api.model.dtos;

import lombok.Builder;

import java.util.Date;

@Builder
public record ForecastDTO(
        NightDTO night,
        DayDTO day,
        Date date,
        String text
) {
}
