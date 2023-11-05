package com.testtask.weather.backend.domain.forecast_daytime.repository;

import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.place.entity.Place;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface ForecastDaytimeRepository {

    @Insert("INSERT INTO forecast_daytime(phenomenon, tempmin, tempmax, text, type) " +
            "VALUES (#{phenomenon}, #{tempmin}, #{tempmax}, #{text}, #{type}) RETURNING id")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(ForecastDaytime forecastDaytime);

    @Select("SELECT * FROM forecast_daytime WHERE id = #{id}")
    ForecastDaytime findById(UUID id);

}