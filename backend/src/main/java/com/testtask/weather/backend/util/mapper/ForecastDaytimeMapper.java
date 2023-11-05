package com.testtask.weather.backend.util.mapper;

import com.testtask.weather.backend.domain.forecast.api.model.dtos.DayDTO;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.NightDTO;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.util.enums.forecast_type.ForecastType;
import org.springframework.stereotype.Component;

@Component
public class ForecastDaytimeMapper {

    public ForecastDaytime mapDayToForecastDaytime(DayDTO dayDTO) {
        return ForecastDaytime.builder()
                .phenomenon(dayDTO.phenomenon())
                .tempmax(dayDTO.tempmax())
                .tempmin(dayDTO.tempmin())
                .text(dayDTO.text())
                .type(ForecastType.DAY)
                .build();
    }

    public ForecastDaytime mapNightToForecastDaytime(NightDTO nightDTO) {
        return ForecastDaytime.builder()
                .phenomenon(nightDTO.phenomenon())
                .tempmax(nightDTO.tempmax())
                .tempmin(nightDTO.tempmin())
                .text(nightDTO.text())
                .type(ForecastType.NIGHT)
                .build();
    }
}
