package com.testtask.weather.backend.domain.forecast_daytime.entity;

import com.testtask.weather.backend.domain.forecast.api.model.PlaceDTO;
import com.testtask.weather.backend.util.enums.forecast_type.ForecastType;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ForecastDaytime {
    private UUID id;
    private String phenomenon;
    private int tempmin;
    private int tempmax;
    private String text;
    private ForecastType type;
}
