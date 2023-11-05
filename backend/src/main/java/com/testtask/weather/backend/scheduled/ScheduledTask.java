package com.testtask.weather.backend.scheduled;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastDTO;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastsDTO;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.PlaceDTO;
import com.testtask.weather.backend.domain.forecast.service.ForecastService;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.forecast_daytime.service.ForecastDaytimeService;
import com.testtask.weather.backend.domain.place.service.PlaceService;
import com.testtask.weather.backend.util.mapper.ForecastDaytimeMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RequiredArgsConstructor
@Service
@Slf4j
public class ScheduledTask {
    String url = "https://www.ilmateenistus.ee/ilma_andmed/xml/forecast.php?lang=eng";
    private final ForecastService forecastService;
    private final PlaceService placeService;
    private final ForecastDaytimeService forecastDaytimeService;
    private final ForecastDaytimeMapper forecastDaytimeMapper;

    @Scheduled(cron = "0 0/30 * * * ?")
    public void fetchForecasts() throws IOException {

        RestTemplate restTemplate = new RestTemplate();
        XmlMapper xmlMapper = new XmlMapper();
        String weatherResponse = restTemplate.getForObject(url, String.class);
        String weatherJson = xmlMapper.readTree(weatherResponse).toString();
        ObjectMapper objectMapper = new ObjectMapper();

        ForecastsDTO forecasts = objectMapper.readValue(weatherJson, ForecastsDTO.class);

        for (ForecastDTO forecastDTO : forecasts.forecast()) {
            ForecastDaytime day = forecastDaytimeMapper.mapDayToForecastDaytime(forecastDTO.day());
            ForecastDaytime night = forecastDaytimeMapper.mapNightToForecastDaytime(forecastDTO.night());

            forecastDaytimeService.createForecastDayime(day);
            forecastDaytimeService.createForecastDayime(night);

            if (forecastDTO.day().place() != null) {
                for (PlaceDTO placeDTO : forecastDTO.day().place()) {
                    placeService.createPlaceForForecastDaytime(day.getId(), placeDTO);
                }
            }
            if (forecastDTO.night().place() != null) {
                for (PlaceDTO placeDTO : forecastDTO.night().place()) {
                    placeService.createPlaceForForecastDaytime(night.getId(), placeDTO);
                }
            }

            forecastService.createForecast(forecastDTO, day.getId(), night.getId());
        }
    }
}
