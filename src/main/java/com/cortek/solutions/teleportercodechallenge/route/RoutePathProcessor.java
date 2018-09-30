package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.util.RoutePathUtil;
import com.cortek.solutions.teleportercodechallenge.util.SimpleRouteUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class RoutePathProcessor {

    public static final String NULL_ROUTES_SET_MESSAGE = "The routes set cannot be null";

    private SimpleRouteUtil simpleRouteUtil;

    private RoutePathUtil routePathUtil;

    @Autowired
    public RoutePathProcessor(SimpleRouteUtil simpleRouteUtil, RoutePathUtil routePathUtil) {
        this.simpleRouteUtil = simpleRouteUtil;
        this.routePathUtil = routePathUtil;
    }

    public Map<String, List<RoutePath>> createRoutePaths(Set<String> inputRoutes) {

        Map<String, List<RoutePath>> routePaths = new HashMap<>();

        Set<SimpleRoute> simpleRoutes = generateSimpleRoutesFromInput(inputRoutes);
        Set<String> uniqueCityNames = extractUniqueCityNames(simpleRoutes);
        Map<String, Set<SimpleRoute>> simpleRoutesByCityNameMap = simpleRouteUtil.buildSimpleRoutesMapByCityNames(uniqueCityNames, simpleRoutes);

        simpleRoutesByCityNameMap.forEach((cityName, simpleRoutesByCityName) -> {
            simpleRoutesByCityName.forEach(simpleRoute -> {
                if (routePaths.containsKey(cityName)) {
                    List<RoutePath> routePathsForCityName = routePaths.get(cityName);
                    routePathsForCityName.add(routePathUtil.buildRoutePath(cityName, simpleRoute, new RoutePath(), simpleRoutes));
                }
            });
        });

        return routePaths;
    }

    public Set<SimpleRoute> generateSimpleRoutesFromInput(Set<String> inputRoutes) {

        Validate.notNull(inputRoutes, NULL_ROUTES_SET_MESSAGE);

        return inputRoutes.stream().map(inputRoute -> SimpleRoute.createSimpleRoute(inputRoute)).collect(Collectors.toSet());
    }

    public Set<String> extractUniqueCityNames(Set<SimpleRoute> simpleRoutes) {

        Set<String> uniqueCityNames = new HashSet<>();

        simpleRoutes.forEach(simpleRoute -> {
            uniqueCityNames.add(simpleRoute.getOriginCityName());
            uniqueCityNames.add(simpleRoute.getDestinationCityName());
        });

        return uniqueCityNames;
    }

}
