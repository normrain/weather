package com.testtask.weather.backend.domain.forecast_daytime.entity;

import com.testtask.weather.backend.util.enums.forecast_type.ForecastType;
import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Jacksonized
public class ForecastDaytime {
    private UUID id;
    private String phenomenon;
    private int tempmin;
    private int tempmax;
    private String text;
    private ForecastType type;
}
