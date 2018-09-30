package com.cortek.solutions.teleportercodechallenge.util;

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

        System.out.println("originCityName: " + originCityName);
        System.out.println("currentSimpleRoute: " + currentSimpleRoute);
        System.out.println("routePath: " + routePath);

        String destinationCityName = currentSimpleRoute.getOriginCityName().equals(originCityName) ? currentSimpleRoute.getDestinationCityName() : currentSimpleRoute.getOriginCityName();
        routePath.getPath().add(originCityName);
        routePath.getPath().add(destinationCityName);
        routePath.getIncludedSimpleRoutes().add(currentSimpleRoute);

        simpleRoutes.forEach(simpleRoute -> {
            System.out.println("Interrogating simpleRoute" + simpleRoute);
            if (simpleRoute.containsCityName(destinationCityName) && !simpleRoute.containsCityName(originCityName) && !routePath.getIncludedSimpleRoutes().contains(simpleRoute)) {
                System.out.print("The simpleRoute contained the destinationCityName: " + destinationCityName + " and the SimpleRoute did not contain the originCityName: " + originCityName + " and the routePath: " + routePath + " did not contain the simpleRoute: " + simpleRoute);
                buildRoutePath(destinationCityName, simpleRoute, routePath, simpleRoutes);
            }
        });

        return routePath;
    }
}
