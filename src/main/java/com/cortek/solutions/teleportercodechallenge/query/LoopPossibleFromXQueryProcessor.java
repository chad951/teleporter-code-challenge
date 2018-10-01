package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputUtil;
import com.cortek.solutions.teleportercodechallenge.output.TeleporterOutputProcessor;
import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class LoopPossibleFromXQueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    private TeleporterOutputProcessor teleporterOutputProcessor;

    @Autowired
    public LoopPossibleFromXQueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil, TeleporterOutputProcessor teleporterOutputProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
        this.teleporterOutputProcessor = teleporterOutputProcessor;
    }

    public void processQueryLines(String[] inputLines, Map<String, RouteNode> routeNodeGraphByCityName, Set<SimpleRoute> simpleRoutes) {

        List<String> loopPossibleFromXQueryLines = teleporterInputUtil.extractLoopPossiblefromXQueryLines(inputLines);

        loopPossibleFromXQueryLines.forEach(loopPossibleFromXQueryLine -> {
            boolean answer = isLoopPossible(loopPossibleFromXQueryLine, routeNodeGraphByCityName, simpleRoutes);
            teleporterOutputProcessor.printLoopPossibleFromXQueryLineResults(loopPossibleFromXQueryLine, answer);
        });
    }

    public boolean isLoopPossible(String loopPossibleFromXQueryLine, Map<String, RouteNode> routeNodeGraphByCityName, Set<SimpleRoute> simpleRoutes) {

        boolean isLoopPossible = false;
        LoopPossibleFromXQuery loopPossibleFromXQuery = queryUtil.parseLoopPossibleFromXQuery(loopPossibleFromXQueryLine);
        String loopCheckCityName = loopPossibleFromXQuery.getLoopCheckCityName();
        RouteNode loopCheckCityNameRouteNode = routeNodeGraphByCityName.get(loopCheckCityName);
        if (loopCheckCityNameRouteNode != null) {
            Set<RouteNode> edgeRouteNodes = findAllEdgeNodesForGivenCityName(loopCheckCityName, loopCheckCityNameRouteNode, new HashSet<>());
            isLoopPossible = edgeRouteNodes.stream().anyMatch(edgeRouteNode -> simpleRoutes.contains(new SimpleRoute(loopCheckCityName, edgeRouteNode.getCityName())));
        }

        return isLoopPossible;
    }

    public Set<RouteNode> findAllEdgeNodesForGivenCityName(String cityName, RouteNode routeNode, Set<RouteNode> currentEdgeRouteNodes) {

        Set<RouteNode> edgeRouteNodes;

        if (currentEdgeRouteNodes == null) {
            edgeRouteNodes = new HashSet<>();
        } else {
            edgeRouteNodes = new HashSet<RouteNode>(){{addAll(currentEdgeRouteNodes);}};
        }

        if (routeNode.getDirectRouteNodes().isEmpty()) {
            edgeRouteNodes.add(routeNode);
        } else {
            routeNode.getDirectRouteNodes().forEach(directRouteNode -> {
                edgeRouteNodes.addAll(findAllEdgeNodesForGivenCityName(cityName, directRouteNode, edgeRouteNodes));
            });
        }

        return edgeRouteNodes;
    }
}
