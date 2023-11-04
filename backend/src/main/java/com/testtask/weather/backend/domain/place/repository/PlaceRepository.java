package com.testtask.weather.backend.domain.place.repository;

import com.testtask.weather.backend.domain.forecast_daytime.entity.ForecastDaytime;
import com.testtask.weather.backend.domain.place.entity.Place;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface PlaceRepository {

    @Insert("INSERT INTO place(forecast_daytime_id, phenomenon, tempmin, tempmax) " +
            "VALUES (#{forecastDaytimeId}, #{phenomenon}, #{tempmin}, #{tempmax}) RETURNING id")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(Place place);
}