package com.testtask.weather.backend.domain.forecast.service;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDTO;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast.repository.ForecastRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;

    @Transactional
    public void createForecast(ForecastDTO forecastDTO, UUID dayId, UUID nightId) {
        forecastRepository.insert(
                Forecast.builder()
                        .text(forecastDTO.text())
                        .date(forecastDTO.date())
                        .dayId(dayId)
                        .nightId(nightId)
                        .build()
        );
    }
}
