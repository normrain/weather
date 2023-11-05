package com.testtask.weather.backend.domain.forecast.api.model;

public record PlaceDTO (
    String name,
    String phenomenon,
    int tempmin,
    int tempmax
) {}
