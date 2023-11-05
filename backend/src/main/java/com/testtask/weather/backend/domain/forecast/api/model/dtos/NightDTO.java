package com.testtask.weather.backend.domain.forecast.api.model.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record NightDTO(
    String phenomenon,
    int tempmin,
    int tempmax,
    String text,
    List<PlaceDTO> place
){}
