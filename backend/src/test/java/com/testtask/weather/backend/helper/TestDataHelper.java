package com.testtask.weather.backend.helper;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDaytimeResponse;
import com.testtask.weather.backend.domain.forecast.api.model.ForecastResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.DayDTO;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastDTO;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.NightDTO;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TestDataHelper {

    public static PlaceResponse getPlaceResponse(UUID id){
        return PlaceResponse.builder()
                .id(id)
                .tempmin(0)
                .tempmax(10)
                .name("TestName")
                .phenomenon("testPhenomenon")
                .build();
    }

    public static ForecastResponse getForecastResponse(UUID id,UUID dayId , UUID nightId) {
        return ForecastResponse.builder()
                .id(id)
                .day(getForecastDaytimeResponse(dayId))
                .night(getForecastDaytimeResponse(nightId))
                .date(new Date())
                .build();
    }

    public static ForecastDaytimeResponse getForecastDaytimeResponse(UUID id) {
        return ForecastDaytimeResponse.builder()
                .id(id)
                .place(List.of(getPlaceResponse(UUID.randomUUID())))
                .tempmin(0)
                .tempmax(10)
                .phenomenon("Test")
                .text("testText")
                .build();
    }

    public static NightDTO getNightDTO() {
        return NightDTO.builder()
                .phenomenon("testPhenomenon")
                .tempmin(0)
                .tempmax(10)
                .place(List.of())
                .text("test")
                .build();
    }

    public static DayDTO getDayDTO() {
        return DayDTO.builder()
                .phenomenon("testPhenomenon")
                .tempmin(0)
                .tempmax(10)
                .place(List.of())
                .text("test")
                .build();
    }

    public static ForecastDTO getForecastDTO() {
        return ForecastDTO.builder()
                .date(new Date())
                .day(getDayDTO())
                .night(getNightDTO())
                .text("")
                .build();
    }

}
