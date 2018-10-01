package com.cortek.solutions.teleportercodechallenge.input;

import com.cortek.solutions.teleportercodechallenge.query.AllQueryProcessor;
import com.cortek.solutions.teleportercodechallenge.route.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class TeleporterInputProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private AllQueryProcessor allQueryProcessor;

    private SimpleRouteUtil simpleRouteUtil;

    private RoutesGraphUtil routesGraphUtil;

    @Autowired
    public TeleporterInputProcessor(TeleporterInputUtil teleporterInputUtil, AllQueryProcessor allQueryProcessor, SimpleRouteUtil simpleRouteUtil, RoutesGraphUtil routesGraphUtil) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.allQueryProcessor = allQueryProcessor;
        this.simpleRouteUtil = simpleRouteUtil;
        this.routesGraphUtil = routesGraphUtil;
    }

    public void processInput(String input) {

        String[] inputLines = input.split("\\r?\\n");
        List<String> inputRoutes = teleporterInputUtil.extractRouteLines(inputLines);
        Set<SimpleRoute> simpleRoutes = simpleRouteUtil.generateSimpleRoutesFromInput(inputRoutes);
        Set<String> uniqueCityNames = simpleRouteUtil.extractUniqueCityNames(simpleRoutes);
        Map<String, RouteNode> routeNodeGraphByCityName = routesGraphUtil.buildRouteNodeGraphByCityName(uniqueCityNames, simpleRoutes);
        allQueryProcessor.processQueries(inputLines, routeNodeGraphByCityName, simpleRoutes);
    }
}
