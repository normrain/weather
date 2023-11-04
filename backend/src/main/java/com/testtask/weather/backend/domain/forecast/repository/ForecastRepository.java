package com.testtask.weather.backend.domain.forecast.repository;

import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ForecastRepository {

    @Insert("INSERT INTO forecast(day_id, night_id, date, text) " +
            "VALUES (#{dayId}, #{nightId}, #{date}, #{text}) RETURNING id")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(Forecast forecast);
}
