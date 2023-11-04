package com.testtask.weather.backend.domain.forecast_daytime.service;

import com.testtask.weather.backend.domain.forecast.api.model.DayDTO;
import com.testtask.weather.backend.domain.forecast.api.model.NightDTO;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.forecast_daytime.repository.ForecastDaytimeRepository;
import com.testtask.weather.backend.util.enums.forecast_type.ForecastType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ForecastDaytimeService {

    private final ForecastDaytimeRepository forecastDaytimeRepository;

    @Transactional
    public void createForecastDayime(ForecastDaytime forecastDaytime) {
        forecastDaytimeRepository.insert(forecastDaytime);
    }

}
