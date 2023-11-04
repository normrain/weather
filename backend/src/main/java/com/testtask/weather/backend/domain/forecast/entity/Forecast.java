package com.testtask.weather.backend.domain.forecast.entity;

import lombok.*;

import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Forecast {
    private UUID id;
    private UUID dayId;
    private UUID nightId;
    private Date date;
    private String text;
}
