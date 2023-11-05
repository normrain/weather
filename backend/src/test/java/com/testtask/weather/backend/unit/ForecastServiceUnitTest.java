package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.model.ForecastDaytimeResponse;
import com.testtask.weather.backend.domain.forecast.api.model.ForecastResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.ForecastDTO;
import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast.repository.ForecastRepository;
import com.testtask.weather.backend.domain.forecast.service.ForecastService;
import com.testtask.weather.backend.domain.forecast_daytime.service.ForecastDaytimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        ForecastDTO forecastDTO = new ForecastDTO("Sample Text", Instant.now());
        forecastService.createForecast(forecastDTO, dayId, nightId);

        verify(forecastRepository, times(1)).insert(any(Forecast.class));
    }

    @Test
    void testGetForecasts() {
        // Mock data setup
        UUID dayId = UUID.randomUUID();
        UUID nightId = UUID.randomUUID();
        Forecast forecast = new Forecast(dayId, nightId, Instant.now(), "Sample Text");
        when(forecastRepository.findAll()).thenReturn(List.of(forecast));

        ForecastDaytimeResponse dayResponse = new ForecastDaytimeResponse();
        ForecastDaytimeResponse nightResponse = new ForecastDaytimeResponse();
        when(forecastDaytimeService.getForecastDaytime(dayId)).thenReturn(dayResponse);
        when(forecastDaytimeService.getForecastDaytime(nightId)).thenReturn(nightResponse);

        // Test
        List<ForecastResponse> forecasts = forecastService.getForecasts();

        // Assertions
        assertNotNull(forecasts);
        assertEquals(1, forecasts.size());
        assertEquals(dayId, forecasts.get(0).getDay().getId());
        assertEquals(nightId, forecasts.get(0).getNight().getId());
        // Add more assertions based on your implementation
    }

    @Test
    void testGetForecastForPlace() {
        // Mock data setup
        UUID dayId = UUID.randomUUID();
        UUID nightId = UUID.randomUUID();
        Forecast forecast = new Forecast(dayId, nightId, Instant.now(), "Sample Text");
        when(forecastRepository.findAll()).thenReturn(List.of(forecast));

        ForecastDaytimeResponse dayResponse = new ForecastDaytimeResponse();
        ForecastDaytimeResponse nightResponse = new ForecastDaytimeResponse();
        dayResponse.setPlace(List.of(new PlaceResponse("Place1")));
        nightResponse.setPlace(List.of(new PlaceResponse("Place2")));
        when(forecastDaytimeService.getForecastDaytime(dayId)).thenReturn(dayResponse);
        when(forecastDaytimeService.getForecastDaytime(nightId)).thenReturn(nightResponse);

        // Test
        List<PlaceResponse> places = forecastService.getForecastForPlace("Place1");

        // Assertions
        assertNotNull(places);
        assertEquals(1, places.size());
        assertEquals("Place1", places.get(0).getName());
        // Add more assertions based on your implementation
    }
}
