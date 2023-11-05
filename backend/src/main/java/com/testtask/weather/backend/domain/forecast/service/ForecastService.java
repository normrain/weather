package com.testtask.weather.backend.domain.forecast.service;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDaytimeResponse;
import com.testtask.weather.backend.domain.forecast.api.model.ForecastResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastDTO;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast.repository.ForecastRepository;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.forecast_daytime.service.ForecastDaytimeService;
import com.testtask.weather.backend.domain.place.entity.Place;
import com.testtask.weather.backend.domain.place.service.PlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForecastService {

    private final ForecastRepository forecastRepository;
    private final ForecastDaytimeService forecastDaytimeService;
    private final PlaceService placeService;

    @Transactional
    public void createForecast(ForecastDTO forecastDTO, UUID dayId, UUID nightId) {
        forecastRepository.insert(
                Forecast.builder()
                        .text(forecastDTO.text())
                        .date(forecastDTO.date())
                        .dayId(dayId)
                        .nightId(nightId)
                        .createdAt(Instant.now())
                        .build()
        );
    }

    public List<ForecastResponse> getForecasts(){
        List<Forecast> forecasts = forecastRepository.findAll();
        List<ForecastResponse> forecastResponse = new ArrayList<>();
        for(Forecast forecast : forecasts){
            ForecastDaytimeResponse dayResponse = forecastDaytimeService.getForecastDaytime(forecast.getDayId());
            ForecastDaytimeResponse nightResponse = forecastDaytimeService.getForecastDaytime(forecast.getNightId());
            forecastResponse.add(
                    ForecastResponse.builder()
                            .id(forecast.getId())
                            .date(forecast.getDate())
                            .night(nightResponse)
                            .day(dayResponse)
                            .build()
            );
        }
        return forecastResponse;
    }

    public List<PlaceResponse> getForecastForPlace(String placeName) {
        List<Forecast> forecasts = forecastRepository.findAll();
        List<PlaceResponse> placeResponse = new ArrayList<>();
        for(Forecast forecast : forecasts) {
            ForecastDaytimeResponse dayResponse = forecastDaytimeService.getForecastDaytime(forecast.getDayId());
            ForecastDaytimeResponse nightResponse = forecastDaytimeService.getForecastDaytime(forecast.getNightId());

            if(dayResponse.place() != null && nightResponse != null) {
                placeResponse.add(dayResponse.place().stream()
                        .filter(place -> placeName.equals(place.name()))
                        .findAny()
                        .orElse(null));
                placeResponse.add(nightResponse.place().stream()
                        .filter(place -> placeName.equals(place.name()))
                        .findAny()
                        .orElse(null));
            }
            return placeResponse;
        }
        return null;
    }

}
