package com.testtask.weather.backend.domain.forecast.api.controller;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast.service.ForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Validated
@RestController
@ControllerAdvice
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/forecast")
@CrossOrigin(origins = "*")
public class ForecastController {

    private final ForecastService forecastService;
    @GetMapping(value = "/", produces = {"application/json"})
    public List<ForecastResponse> getForecasts() {
        return forecastService.getForecasts();
    }

    @GetMapping(value="/{place}")
    public List<PlaceResponse> getForecastForPlace(@PathVariable String place){
        return forecastService.getForecastForPlace(place);

    }
}
