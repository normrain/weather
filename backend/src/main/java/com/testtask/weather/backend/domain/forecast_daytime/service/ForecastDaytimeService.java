package com.testtask.weather.backend.domain.forecast_daytime.service;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDaytimeResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.forecast_daytime.repository.ForecastDaytimeRepository;
import com.testtask.weather.backend.domain.place.entity.Place;
import com.testtask.weather.backend.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ForecastDaytimeService {

    private final ForecastDaytimeRepository forecastDaytimeRepository;
    private final PlaceService placeService;

    @Transactional
    public void createForecastDayime(ForecastDaytime forecastDaytime) {
        forecastDaytimeRepository.insert(forecastDaytime);
    }

    public ForecastDaytimeResponse getForecastDaytime(UUID id) {
        ForecastDaytime forecastDaytime = forecastDaytimeRepository.findById(id);
        List<PlaceResponse> places = placeService.getPlacesForForecastDaytime(id);
        return ForecastDaytimeResponse.builder()
                .id(id)
                .phenomenon(forecastDaytime.getPhenomenon())
                .text(forecastDaytime.getText())
                .tempmax(forecastDaytime.getTempmax())
                .tempmin(forecastDaytime.getTempmin())
                .place(places)
                .build();


    }

}
