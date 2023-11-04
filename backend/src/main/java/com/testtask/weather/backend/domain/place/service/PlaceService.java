package com.testtask.weather.backend.domain.place.service;

import com.testtask.weather.backend.domain.forecast.api.model.PlaceDTO;
import com.testtask.weather.backend.domain.place.entity.Place;
import com.testtask.weather.backend.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;


    @Transactional
    public void createPlaceForForecastDaytime(UUID forecastDayTimeId, PlaceDTO placeDTO) {
        Place placeToBeCreated = Place.builder()
                .forecastDaytimeId(forecastDayTimeId)
                .phenomenon(placeDTO.phenomenon())
                .tempmax(placeDTO.tempmax())
                .tempmin(placeDTO.tempmin())
                .build();
        placeRepository.insert(placeToBeCreated);
    }
}
