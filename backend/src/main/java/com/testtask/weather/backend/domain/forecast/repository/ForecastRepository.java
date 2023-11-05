package com.testtask.weather.backend.domain.forecast.repository;

import com.testtask.weather.backend.domain.forecast.entity.Forecast;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Mapper
@Repository
public interface ForecastRepository {

    @Insert("INSERT INTO forecast(day_id, night_id, date, text, created_at) " +
            "VALUES (#{dayId}, #{nightId}, #{date}, #{text}, #{createdAt}) RETURNING id")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Forecast forecast);

    @Select("SELECT * FROM forecast ORDER BY created_at LIMIT 4")
    @Result(property = "dayId", column = "day_id")
    @Result(property = "nightId", column = "night_id")
    List<Forecast> findAll();
}
