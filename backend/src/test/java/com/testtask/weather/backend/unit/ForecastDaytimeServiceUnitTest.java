package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDaytimeResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.forecast_daytime.repository.ForecastDaytimeRepository;
import com.testtask.weather.backend.domain.forecast_daytime.service.ForecastDaytimeService;
import com.testtask.weather.backend.domain.place.service.PlaceService;
import com.testtask.weather.backend.util.enums.forecast_type.ForecastType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@Tag("unit")
@RunWith(MockitoJUnitRunner.class)
public class ForecastDaytimeServiceUnitTest {

    @Mock
    private ForecastDaytimeRepository forecastDaytimeRepository;

    @Mock
    private PlaceService placeService;

    @InjectMocks
    private ForecastDaytimeService forecastDaytimeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateForecastDaytime() {
        UUID id = UUID.randomUUID();
        ForecastDaytime forecastDaytime = new ForecastDaytime(
                id,
                "testPhenomenon",
                0,
                10,
                "test",
                ForecastType.DAY
        );

        forecastDaytimeService.createForecastDayime(forecastDaytime);

        verify(forecastDaytimeRepository, times(1)).insert(any(ForecastDaytime.class));

    }

    @Test
    void testGetForecastDaytime() {
        UUID id = UUID.randomUUID();
        ForecastDaytime forecastDaytime = new ForecastDaytime(
        id,
        "testPhenomenon",
        0,
        10,
        "test",
        ForecastType.DAY
        );

        when(forecastDaytimeRepository.findById(id)).thenReturn(forecastDaytime);

        List<PlaceResponse> places = List.of(new PlaceResponse(
                UUID.randomUUID(),
                "testName",
                "testPhenomenon",
                0,
                10));
        when(placeService.getPlacesForForecastDaytime(id)).thenReturn(places);

        ForecastDaytimeResponse response = forecastDaytimeService.getForecastDaytime(id);

        verify(forecastDaytimeRepository, times(1)).findById(id);
        verify(placeService, times(1)).getPlacesForForecastDaytime(id);

        assertNotNull(response);
        assertThat(id).isEqualTo(response.id());
    }
}
