package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.PlaceDTO;
import com.testtask.weather.backend.domain.place.entity.Place;
import com.testtask.weather.backend.domain.place.repository.PlaceRepository;
import com.testtask.weather.backend.domain.place.service.PlaceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
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
        List<Place> places = List.of(
                new Place(UUID.randomUUID(), "PlaceName", forecastDaytimeId, "Sunny", 30, 20)
        );

        when(placeRepository.findById(forecastDaytimeId)).thenReturn(places);

        List<PlaceResponse> result = placeService.getPlacesForForecastDaytime(forecastDaytimeId);


        assertNotNull(result);
        assertEquals(result.size(), places.size());
        assertEquals("PlaceName", result.get(0).name());
    }
}
