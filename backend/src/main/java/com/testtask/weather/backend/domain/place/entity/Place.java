package com.testtask.weather.backend.domain.place.entity;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Place {
    private UUID id;
    private UUID forecastDaytimeId;
    private String phenomenon;
    private int tempmin;
    private int tempmax;

}
