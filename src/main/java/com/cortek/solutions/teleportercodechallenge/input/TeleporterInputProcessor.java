package com.cortek.solutions.teleportercodechallenge.input;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.route.RoutePathProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class TeleporterInputProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private RoutePathProcessor routePathProcessor;

    @Autowired
    public TeleporterInputProcessor(TeleporterInputUtil teleporterInputUtil, RoutePathProcessor routePathProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.routePathProcessor = routePathProcessor;
    }

    public void processInput(String input) {

        String[] inputLines = input.split("\\r?\\n");

        Set<String> inputRoutes = teleporterInputUtil.extractRouteLines(inputLines);
        Map<String, Set<RoutePath>> routePathsByCityName = routePathProcessor.generateRoutePaths(inputRoutes);
    }
}
