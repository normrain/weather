package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.PlaceDTO;
import com.testtask.weather.backend.domain.place.entity.Place;
import com.testtask.weather.backend.domain.place.repository.PlaceRepository;
import com.testtask.weather.backend.domain.place.service.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PlaceServiceUnitTest {

    @Mock
    private PlaceRepository placeRepository;

    @InjectMocks
    private PlaceService placeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreatePlaceForForecastDaytime() {
        UUID forecastDaytimeId = UUID.randomUUID();
        PlaceDTO placeDTO = new PlaceDTO("PlaceName", "Sunny", 30, 20);
        placeService.createPlaceForForecastDaytime(forecastDaytimeId, placeDTO);

        verify(placeRepository, times(1)).insert(any(Place.class));
    }

    @Test
    void testGetPlacesForForecastDaytime() {
        UUID forecastDaytimeId = UUID.randomUUID();
        Place place = new Place(forecastDaytimeId, "PlaceName", "Sunny", 30, 20);
        when(placeRepository.findById(forecastDaytimeId)).thenReturn(List.of(place));

        List<PlaceResponse> places = placeService.getPlacesForForecastDaytime(forecastDaytimeId);

        verify(placeRepository, times(1)).findById(forecastDaytimeId);
        assertNotNull(places);
        assertEquals(1, places.size());
        assertEquals("PlaceName", places.get(0).getName());
        // Add more assertions based on your implementation
    }
}
