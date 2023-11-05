package com.testtask.weather.backend.domain.place.service;

import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.PlaceDTO;
import com.testtask.weather.backend.domain.place.entity.Place;
import com.testtask.weather.backend.domain.place.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;


    @Transactional
    public void createPlaceForForecastDaytime(UUID forecastDayTimeId, PlaceDTO placeDTO) {
        Place placeToBeCreated = Place.builder()
                .name(placeDTO.name())
                .forecastDaytimeId(forecastDayTimeId)
                .phenomenon(placeDTO.phenomenon())
                .tempmax(placeDTO.tempmax())
                .tempmin(placeDTO.tempmin())
                .build();
        placeRepository.insert(placeToBeCreated);
    }

    public List<PlaceResponse> getPlacesForForecastDaytime(UUID forecastDaytimeId) {
        List<Place> places = placeRepository.findById(forecastDaytimeId);
        return places.stream().map(place ->
                        PlaceResponse.builder()
                                .id(place.getId())
                                .name(place.getName())
                                .phenomenon(place.getPhenomenon())
                                .tempmax(place.getTempmax())
                                .tempmin(place.getTempmin())
                                .build())
                .collect(Collectors.toList());
    }
}
