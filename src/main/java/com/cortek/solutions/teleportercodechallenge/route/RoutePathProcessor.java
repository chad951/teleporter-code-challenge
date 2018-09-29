package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.util.TeleporterInputUtil;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class RoutePathProcessor {

    public static final String NULL_ROUTES_SET_MESSAGE = "The routes set cannot be null";

    private TeleporterInputUtil teleporterInputUtil;

    @Autowired
    public RoutePathProcessor(TeleporterInputUtil teleporterInputUtil) {
        this.teleporterInputUtil = teleporterInputUtil;
    }

    public Map<String, List<String[]>> createRoutePaths(Set<String> routes) {

        Map<String, List<String[]>> routePaths = new HashMap<String, List<String[]>>();

        return routePaths;
    }

    public Map<String, String[]> createRouteMap(Set<String> routes) {

        Validate.notNull(routes, NULL_ROUTES_SET_MESSAGE);

        Map<String, String[]> routeMap = routes
                .stream()
                .map(route -> teleporterInputUtil.parseRouteLineIntoCityNameArray(route))
                .collect(Collectors.toMap(cityNameArray -> cityNameArray[0], cityNameArray -> cityNameArray));

        return routeMap;
    }

}
