package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class RoutePathUtil {

    public static final String NULL_ORIGIN_CITY_NAME_MESSAGE = "The origin city name cannot be null";
    public static final String NULL_CURRENT_SIMPLE_ROUTE_MESSAGE = "The current simple route name cannot be null";
    public static final String NULL_ROUTE_PATH_MESSAGE = "The route path cannot be null";
    public static final String NULL_SIMPLE_ROUTE_SET_MESSAGE = "The simple route set cannot be null";

    public RoutePath buildRoutePath(final String originCityName, final SimpleRoute currentSimpleRoute, final RoutePath routePath, final Set<SimpleRoute> simpleRoutes) {

        Validate.notNull(originCityName, NULL_ORIGIN_CITY_NAME_MESSAGE);
        Validate.notNull(currentSimpleRoute, NULL_CURRENT_SIMPLE_ROUTE_MESSAGE);
        Validate.notNull(routePath, NULL_ROUTE_PATH_MESSAGE);
        Validate.notNull(simpleRoutes, NULL_SIMPLE_ROUTE_SET_MESSAGE);

        String destinationCityName = currentSimpleRoute.getOriginCityName().equals(originCityName) ? currentSimpleRoute.getDestinationCityName() : currentSimpleRoute.getOriginCityName();
        if (!routePath.getPath().contains(originCityName)) {
            routePath.getPath().add(originCityName);
        }
        routePath.getPath().add(destinationCityName);
        routePath.getIncludedSimpleRoutes().add(currentSimpleRoute);

        simpleRoutes.forEach(simpleRoute -> {
            if (simpleRoute.containsCityName(destinationCityName) && !simpleRoute.containsCityName(originCityName) && !routePath.getIncludedSimpleRoutes().contains(simpleRoute)) {
                buildRoutePath(destinationCityName, simpleRoute, routePath, simpleRoutes);
            }
        });

        return routePath;
    }
}
