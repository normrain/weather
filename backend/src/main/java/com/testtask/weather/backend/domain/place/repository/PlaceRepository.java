package com.testtask.weather.backend.domain.place.repository;

import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.place.entity.Place;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface PlaceRepository {

    @Insert("INSERT INTO place(name, forecast_daytime_id, phenomenon, tempmin, tempmax) " +
            "VALUES (#{name}, #{forecastDaytimeId}, #{phenomenon}, #{tempmin}, #{tempmax}) RETURNING id")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Place place);

    @Select("SELECT * FROM place WHERE forecast_daytime_id = #{forecastDaytimeId}")
    @Result(property = "forecastDaytimeId", column = "forecast_daytime_id")
    List<Place> findById(UUID forecastDaytimeId);

}