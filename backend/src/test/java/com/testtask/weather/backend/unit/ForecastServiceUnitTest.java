package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDaytimeResponse;
import com.testtask.weather.backend.domain.forecast.api.model.ForecastResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastDTO;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast.repository.ForecastRepository;
import com.testtask.weather.backend.domain.forecast.service.ForecastService;
import com.testtask.weather.backend.domain.forecast_daytime.service.ForecastDaytimeService;
import com.testtask.weather.backend.helper.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ForecastServiceUnitTest {

    @Mock
    private ForecastRepository forecastRepository;

    @Mock
    private ForecastDaytimeService forecastDaytimeService;

    @InjectMocks
    private ForecastService forecastService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateForecast() {
        UUID dayId = UUID.randomUUID();
        UUID nightId = UUID.randomUUID();
        ForecastDTO forecastDTO = TestDataHelper.getForecastDTO();
        forecastService.createForecast(forecastDTO, dayId, nightId);

        verify(forecastRepository, times(1)).insert(any(Forecast.class));
    }

    @Test
    void testGetForecasts() {
        // Mock data setup
        UUID dayId = UUID.randomUUID();
        UUID nightId = UUID.randomUUID();
        Forecast forecast = new Forecast(UUID.randomUUID(), dayId, nightId, new Date(), "Test",Instant.now());
        when(forecastRepository.findAll()).thenReturn(List.of(forecast));

        ForecastDaytimeResponse dayResponse = TestDataHelper.getForecastDaytimeResponse(dayId);
        ForecastDaytimeResponse nightResponse = TestDataHelper.getForecastDaytimeResponse(nightId);
        when(forecastDaytimeService.getForecastDaytime(dayId)).thenReturn(dayResponse);
        when(forecastDaytimeService.getForecastDaytime(nightId)).thenReturn(nightResponse);

        // Test
        List<ForecastResponse> forecasts = forecastService.getForecasts();

        // Assertions
        assertNotNull(forecasts);
        assertEquals(1, forecasts.size());
        assertEquals(dayId, forecasts.get(0).day().id());
        assertEquals(nightId, forecasts.get(0).night().id());
    }

    @Test
    void testGetForecastForPlace() {
        // Mock data setup
        UUID dayId = UUID.randomUUID();
        UUID nightId = UUID.randomUUID();
        Forecast forecast = new Forecast(UUID.randomUUID(), dayId, nightId, new Date(), "Sample Text",Instant.now());
        when(forecastRepository.findAll()).thenReturn(List.of(forecast));

        ForecastDaytimeResponse dayResponse = TestDataHelper.getForecastDaytimeResponse(dayId);
        ForecastDaytimeResponse nightResponse = TestDataHelper.getForecastDaytimeResponse(nightId);
        when(forecastDaytimeService.getForecastDaytime(dayId)).thenReturn(dayResponse);
        when(forecastDaytimeService.getForecastDaytime(nightId)).thenReturn(nightResponse);

        // Test
        List<PlaceResponse> places = forecastService.getForecastForPlace("TestName");

        // Assertions
        assertNotNull(places);
        assertEquals(2, places.size());
        assertEquals("TestName", places.get(0).name());
        assertEquals("TestName", places.get(1).name());

    }
}
