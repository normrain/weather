package com.testtask.weather.backend.unit;

import com.testtask.weather.backend.domain.forecast.api.model.dtos.DayDTO;
import com.testtask.weather.backend.domain.forecast.api.model.dtos.NightDTO;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.util.enums.forecast_type.ForecastType;
import com.testtask.weather.backend.util.mapper.ForecastDaytimeMapper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ForecastDaytimeMapperUnitTest {

    @Test
    void testMapDayToForecastDaytime() {
        // Arrange
        DayDTO dayDTO = new DayDTO("Sunny", 30, 20, "Clear sky", List.of());

        // Act
        ForecastDaytimeMapper forecastDaytimeMapper = new ForecastDaytimeMapper();
        ForecastDaytime result = forecastDaytimeMapper.mapDayToForecastDaytime(dayDTO);

        // Assert
        assertEquals(dayDTO.phenomenon(), result.getPhenomenon());
        assertEquals(dayDTO.tempmax(), result.getTempmax());
        assertEquals(dayDTO.tempmin(), result.getTempmin());
        assertEquals(dayDTO.text(), result.getText());
        assertEquals(ForecastType.DAY, result.getType());
    }

    @Test
    void testMapNightToForecastDaytime() {
        // Arrange
        NightDTO nightDTO = new NightDTO("Clear", 25, 15, "Partly cloudy", List.of());

        // Act
        ForecastDaytimeMapper forecastDaytimeMapper = new ForecastDaytimeMapper();
        ForecastDaytime result = forecastDaytimeMapper.mapNightToForecastDaytime(nightDTO);

        // Assert
        assertEquals(nightDTO.phenomenon(), result.getPhenomenon());
        assertEquals(nightDTO.tempmax(), result.getTempmax());
        assertEquals(nightDTO.tempmin(), result.getTempmin());
        assertEquals(nightDTO.text(), result.getText());
        assertEquals(ForecastType.NIGHT, result.getType());
    }
}
