package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputUtil;
import com.cortek.solutions.teleportercodechallenge.output.TeleporterOutputProcessor;
import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class CanITeleportFromXToYQueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    private TeleporterOutputProcessor teleporterOutputProcessor;

    public CanITeleportFromXToYQueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil, TeleporterOutputProcessor teleporterOutputProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
        this.teleporterOutputProcessor = teleporterOutputProcessor;
    }

    public void processQueryLines(String[] inputLines, Map<String, RouteNode> routeNodeGraphByCityName) {

        List<String> canITeleportFromXToYQueryLines = teleporterInputUtil.extractCanITeleportFromXToYQueryLines(inputLines);

        canITeleportFromXToYQueryLines.forEach(citiesFromXInNJumpsQueryLine -> {
            boolean answer = canTeleportFromXToY(citiesFromXInNJumpsQueryLine, routeNodeGraphByCityName);
            teleporterOutputProcessor.printCanITeleportFromXToYQueryLineResults(citiesFromXInNJumpsQueryLine, answer);
        });
    }

    public boolean canTeleportFromXToY(String canITeleportFromXToYQueryLine, Map<String, RouteNode> routeNodeGraphByCityName) {

        CanITeleportFromXToYQuery canITeleportFromXToYQuery = queryUtil.parseCanITeleportFromXToYQuery(canITeleportFromXToYQueryLine);

        RouteNode originCityRouteNode = routeNodeGraphByCityName.get(canITeleportFromXToYQuery.getTeleportOriginCityName());

        return originCityRouteNode.getDirectRouteNodes().stream().anyMatch(directRouteNode -> haveFoundDestinationCity(directRouteNode, canITeleportFromXToYQuery.getTeleportDestinationCityName()));
    }

    public boolean haveFoundDestinationCity(RouteNode routeNode, String targetDestinationCityName) {

        AtomicBoolean haveFoundDestinationCityName = new AtomicBoolean(false);

        if (routeNode.getCityName().equals(targetDestinationCityName)) {
            return true;
        } else {
           routeNode.getDirectRouteNodes().forEach(directRouteNode -> {
               haveFoundDestinationCityName.set(haveFoundDestinationCity(directRouteNode, targetDestinationCityName));
           });
        }
        return haveFoundDestinationCityName.get();
    }
}
