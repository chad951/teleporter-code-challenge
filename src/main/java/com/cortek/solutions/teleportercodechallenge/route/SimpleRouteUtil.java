package com.cortek.solutions.teleportercodechallenge.route;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class SimpleRouteUtil {

    public static final String NULL_CITY_NAME_MESSAGE = "The city name cannot be null";
    public static final String NULL_CITY_NAME_SET_MESSAGE = "The city name set cannot be null";
    public static final String NULL_SIMPLE_ROUTES_MESSAGE = "The simple routes set cannot be null";
    public static final String NULL_ROUTES_SET_MESSAGE = "The routes set cannot be null";

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

    public Set<SimpleRoute> generateSimpleRoutesFromInput(List<String> inputRoutes) {

        Validate.notNull(inputRoutes, NULL_ROUTES_SET_MESSAGE);

        return inputRoutes.stream().map(inputRoute -> SimpleRoute.createSimpleRoute(inputRoute)).collect(Collectors.toSet());
    }

    public Set<String> extractUniqueCityNames(Set<SimpleRoute> simpleRoutes) {

        Set<String> uniqueCityNames = new HashSet<>();

        simpleRoutes.forEach(simpleRoute -> {
            uniqueCityNames.add(simpleRoute.getCityName1());
            uniqueCityNames.add(simpleRoute.getCityName2());
        });

        return uniqueCityNames;
    }
}
