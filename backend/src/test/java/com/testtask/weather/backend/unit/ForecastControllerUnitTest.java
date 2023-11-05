package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.controller.ForecastController;
import com.testtask.weather.backend.domain.forecast.api.model.ForecastResponse;
import com.testtask.weather.backend.domain.forecast.api.model.PlaceResponse;
import com.testtask.weather.backend.domain.forecast.service.ForecastService;
import com.testtask.weather.backend.helper.TestDataHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.hasSize;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@RunWith(MockitoJUnitRunner.class)
public class ForecastControllerUnitTest {

    @Mock
    private ForecastService forecastService;

    @InjectMocks
    private ForecastController forecastController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(forecastController).build();
    }

    @Test
    void testGetForecasts() throws Exception {
        // Arrange
        List<ForecastResponse> forecastResponses = List.of(
                TestDataHelper.getForecastResponse(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID()),
                TestDataHelper.getForecastResponse(UUID.randomUUID(),UUID.randomUUID(),UUID.randomUUID())
        );
        when(forecastService.getForecasts()).thenReturn(forecastResponses);

        // Act & Assert
        mockMvc.perform(get("/api/v1/forecast/")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").exists())
                .andExpect(jsonPath("$[1].id").exists())
                .andExpect(jsonPath("$", hasSize(2)));

        // Verify interaction
        verify(forecastService, times(1)).getForecasts();
    }

}
