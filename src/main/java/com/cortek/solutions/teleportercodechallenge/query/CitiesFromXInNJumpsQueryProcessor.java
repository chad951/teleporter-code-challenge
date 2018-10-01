package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputUtil;
import com.cortek.solutions.teleportercodechallenge.output.TeleporterOutputProcessor;
import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CitiesFromXInNJumpsQueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    private TeleporterOutputProcessor teleporterOutputProcessor;

    @Autowired
    public CitiesFromXInNJumpsQueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil, TeleporterOutputProcessor teleporterOutputProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
        this.teleporterOutputProcessor = teleporterOutputProcessor;
    }

    public void processQueryLines(String[] inputLines, Map<String, RouteNode> routeNodeGraphByCityName) {

        List<String> citiesFromXInNJumpsQueryLines = teleporterInputUtil.extractCitiesFromXInNJumpsQueryLines(inputLines);

        citiesFromXInNJumpsQueryLines.forEach(citiesFromXInNJumpsQueryLine -> {
            Set<String> cityNamesJumped = findCitiesNamesJumped(citiesFromXInNJumpsQueryLine, routeNodeGraphByCityName);
            teleporterOutputProcessor.printCitiesFromXInNJumpsQueryLineResults(citiesFromXInNJumpsQueryLine, cityNamesJumped);
        });
    }

    public Set<String> findCitiesNamesJumped(String citiesFromXInNJumpsQueryLine, Map<String, RouteNode> routeNodeGraphByCityName) {

        Set<String> cityNamesJumped = new HashSet<>();
        CitiesFromXInNJumpsQuery citiesFromXInNJumpsQuery = queryUtil.parseCitiesFromXInNJumpsQuery(citiesFromXInNJumpsQueryLine);
        String originCityName = citiesFromXInNJumpsQuery.getOriginCityName();
        Integer maxNumberOfJumps = citiesFromXInNJumpsQuery.getMaxNumberOfJumps();

        RouteNode routeNode = routeNodeGraphByCityName.get(originCityName);

       routeNode.getDirectRouteNodes().forEach(directRouteNode -> {
           Integer currentNumberOfJumps = 0;
           cityNamesJumped.addAll(buildCityNamesJumpedSet(directRouteNode, maxNumberOfJumps, currentNumberOfJumps, new HashSet<>()));
       });

        return cityNamesJumped;
    }

    public Set<String> buildCityNamesJumpedSet(RouteNode routeNode, Integer maxNumberOfJumps, Integer currentNumberOfJumps, Set<String> currentJumpSet) {

        Set<String> cityNamesJumpedSet;

        if (currentJumpSet == null) {
            cityNamesJumpedSet = new HashSet<>();
        } else {
            cityNamesJumpedSet = new HashSet<String>(){{addAll(currentJumpSet);}};
        }

        Integer curNumberOfJumps = currentNumberOfJumps + 1;
        if (curNumberOfJumps <= maxNumberOfJumps) {
            cityNamesJumpedSet.add(routeNode.getCityName());
        }

        List<RouteNode> directRouteNodes = routeNode.getDirectRouteNodes();
        if (!directRouteNodes.isEmpty()) {
            directRouteNodes.forEach(directRouteNode -> {
                cityNamesJumpedSet.addAll(buildCityNamesJumpedSet(directRouteNode, maxNumberOfJumps, curNumberOfJumps, currentJumpSet));

            });
        }

        return cityNamesJumpedSet;
    }

}
