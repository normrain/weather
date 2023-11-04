package com.testtask.weather.backend.domain.forecast_daytime.repository;

import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ForecastDaytimeRepository {

    @Insert("INSERT INTO forecast_daytime(phenomenon, tempmin, tempmax, text, type) " +
            "VALUES (#{phenomenon}, #{tempmin}, #{tempmax}, #{text}, #{type}) RETURNING id")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(ForecastDaytime forecastDaytime);

}