package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.util.RoutePathUtil;
import com.cortek.solutions.teleportercodechallenge.util.SimpleRouteUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoutePathProcessor {

    public static final String NULL_INPUT_ROUTES_SET_MESSAGE = "The input routes set cannot be null";

    private SimpleRouteUtil simpleRouteUtil;

    private RoutePathUtil routePathUtil;

    @Autowired
    public RoutePathProcessor(SimpleRouteUtil simpleRouteUtil, RoutePathUtil routePathUtil) {
        this.simpleRouteUtil = simpleRouteUtil;
        this.routePathUtil = routePathUtil;
    }

    public Map<String, Set<RoutePath>> generateRoutePaths(Set<String> inputRoutes) {

        Validate.notNull(inputRoutes, NULL_INPUT_ROUTES_SET_MESSAGE);

        Map<String, Set<RoutePath>> routePathsByCityName = new HashMap<>();

        Set<SimpleRoute> simpleRoutes = simpleRouteUtil.generateSimpleRoutesFromInput(inputRoutes);
        Set<String> uniqueCityNames = simpleRouteUtil.extractUniqueCityNames(simpleRoutes);
        Map<String, Set<SimpleRoute>> simpleRoutesByCityNameMap = simpleRouteUtil.buildSimpleRoutesMapByCityNames(uniqueCityNames, simpleRoutes);

        simpleRoutesByCityNameMap.forEach((cityName, simpleRoutesByCityName) -> {
            simpleRoutesByCityName.forEach(simpleRoute -> {
                RoutePath routePathForCityName = routePathUtil.buildRoutePath(cityName, simpleRoute, new RoutePath(), simpleRoutes);

                if (routePathsByCityName.containsKey(cityName)) {
                    Set<RoutePath> routePathsForCityName = routePathsByCityName.get(cityName);
                    routePathsForCityName.add(routePathForCityName);
                } else {
                    Set<RoutePath> routePathsForCityName = new HashSet<>();
                    routePathsForCityName.add(routePathForCityName);
                    routePathsByCityName.put(cityName, routePathsForCityName);
                }
            });
        });

        return routePathsByCityName;
    }

}
