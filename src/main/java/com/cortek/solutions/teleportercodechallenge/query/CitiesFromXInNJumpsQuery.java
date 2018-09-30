package com.cortek.solutions.teleportercodechallenge.query;

import lombok.Data;
import lombok.NonNull;

@Data
public class CitiesFromXInNJumpsQuery {

    @NonNull
    private String originCityName;

    @NonNull
    private Integer maxNumberOfJumps;
}
