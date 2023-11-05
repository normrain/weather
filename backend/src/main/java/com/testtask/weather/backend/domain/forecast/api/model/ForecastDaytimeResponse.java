package com.testtask.weather.backend.domain.forecast.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.jackson.Jacksonized;

import java.util.List;
import java.util.UUID;

@Builder
@Jacksonized
public record ForecastDaytimeResponse (
     UUID id,
     String phenomenon,
     int tempmin,
     int tempmax,
     String text,
     List<PlaceResponse> place
) {}
