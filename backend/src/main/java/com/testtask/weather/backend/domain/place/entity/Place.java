package com.testtask.weather.backend.domain.place.entity;

import lombok.*;
import lombok.extern.jackson.Jacksonized;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Jacksonized
public class Place {
    private UUID id;
    private String name;
    private UUID forecastDaytimeId;
    private String phenomenon;
    private int tempmin;
    private int tempmax;

}
