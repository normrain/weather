package com.testtask.weather.backend.domain.forecast.api.model.dtos;

import java.util.Date;

public record ForecastDTO (
    NightDTO night,
    DayDTO day,
    Date date,
    String text
) {}
