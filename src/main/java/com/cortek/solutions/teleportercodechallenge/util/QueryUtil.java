package com.cortek.solutions.teleportercodechallenge.util;

import com.cortek.solutions.teleportercodechallenge.query.CitiesFromXInNJumpsQuery;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

@Component
public class QueryUtil {

    public static final String NULL_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE = "The cities from X in N jumps query string cannot be null";
    public static final String EMPTY_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE = "The cities from X in N jumps query string cannot be empty";

    public CitiesFromXInNJumpsQuery parseCitiesFromXInNJumpsQuery(String citiesFromXInNJumpsQueryInput) {

        Validate.notNull(citiesFromXInNJumpsQueryInput, NULL_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);
        Validate.notEmpty(citiesFromXInNJumpsQueryInput.trim(), EMPTY_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);

        String[] queryLineElements = citiesFromXInNJumpsQueryInput.split(" ");

        return new CitiesFromXInNJumpsQuery(queryLineElements[2], Integer.parseInt(queryLineElements[4]));
    }
}
