package com.cortek.solutions.teleportercodechallenge.query;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

@Component
public class QueryUtil {

    public static final String NULL_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE = "The cities from X in N jumps query string cannot be null";
    public static final String EMPTY_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE = "The cities from X in N jumps query string cannot be empty";

    public static final String NULL_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE = "The can I teleport from X to Y query string cannot be null";
    public static final String EMPTY_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE = "The can I teleport from X to Y query string cannot be empty";

    public static final String NULL_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE = "The loop possible from X query string cannot be null";
    public static final String EMPTY_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE = "The loop possible from X query string cannot be empty";

    public CitiesFromXInNJumpsQuery parseCitiesFromXInNJumpsQuery(String citiesFromXInNJumpsQueryInput) {

        Validate.notNull(citiesFromXInNJumpsQueryInput, NULL_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);
        Validate.notEmpty(citiesFromXInNJumpsQueryInput.trim(), EMPTY_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);

        String[] queryLineElements = citiesFromXInNJumpsQueryInput.split(" ");

        return new CitiesFromXInNJumpsQuery(queryLineElements[2], Integer.parseInt(queryLineElements[4]));
    }

    public CanITeleportFromXToYQuery parseCanITeleportFromXToYQuery(String canITeleportFromXToYQueryInput) {

        Validate.notNull(canITeleportFromXToYQueryInput, NULL_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE);
        Validate.notEmpty(canITeleportFromXToYQueryInput.trim(), EMPTY_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE);

        String[] queryLineElements = canITeleportFromXToYQueryInput.split(" ");

        return new CanITeleportFromXToYQuery(queryLineElements[4], queryLineElements[6]);
    }

    public LoopPossibleFromXQuery parseLoopPossibleFromXQuery(String loopPossibleFromXQueryInput) {

        Validate.notNull(loopPossibleFromXQueryInput, NULL_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE);
        Validate.notEmpty(loopPossibleFromXQueryInput.trim(), EMPTY_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE);

        String[] queryLineElements = loopPossibleFromXQueryInput.split(" ");

        return new LoopPossibleFromXQuery(queryLineElements[3]);
    }
}
