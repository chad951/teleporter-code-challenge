package com.cortek.solutions.teleportercodechallenge.util;

import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SimpleRouteUtil {

    public static final String NULL_CITY_NAME_MESSAGE = "The city name cannot be null";
    public static final String NULL_CITY_NAME_SET_MESSAGE = "The city name set cannot be null";
    public static final String NULL_SIMPLE_ROUTES_MESSAGE = "The simple routes set cannot be null";

    public Set<SimpleRoute> findAllSimpleRoutesForCityName(String cityName, Set<SimpleRoute> simpleRoutes) {

        Validate.notNull(cityName, NULL_CITY_NAME_MESSAGE);
        Validate.notNull(simpleRoutes, NULL_SIMPLE_ROUTES_MESSAGE);

        return simpleRoutes
                .stream()
                .filter(simpleRoute -> simpleRoute.containsCityName(cityName))
                .collect(Collectors.toSet());
    }

    public Map<String, Set<SimpleRoute>> buildSimpleRoutesMapByCityNames(Set<String> cityNames, Set<SimpleRoute> simpleRoutes) {

        Validate.notNull(cityNames, NULL_CITY_NAME_SET_MESSAGE);
        Validate.notNull(simpleRoutes, NULL_SIMPLE_ROUTES_MESSAGE);

        Map<String, Set<SimpleRoute>> simpleRoutesMapByCityNames = new HashMap<>();

        cityNames.forEach(cityName -> simpleRoutesMapByCityNames.put(cityName, findAllSimpleRoutesForCityName(cityName, simpleRoutes)));

        return simpleRoutesMapByCityNames;
    }
}
