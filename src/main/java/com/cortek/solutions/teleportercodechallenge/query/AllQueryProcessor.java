package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class AllQueryProcessor {

    private CitiesFromXInNJumpsQueryProcessor citiesFromXInNJumpsQueryProcessor;

    private CanITeleportFromXToYQueryProcessor canITeleportFromXToYQueryProcessor;

    private LoopPossibleFromXQueryProcessor loopPossibleFromXQueryProcessor;

    @Autowired
    public AllQueryProcessor(CitiesFromXInNJumpsQueryProcessor citiesFromXInNJumpsQueryProcessor, CanITeleportFromXToYQueryProcessor canITeleportFromXToYQueryProcessor, LoopPossibleFromXQueryProcessor loopPossibleFromXQueryProcessor) {
        this.citiesFromXInNJumpsQueryProcessor = citiesFromXInNJumpsQueryProcessor;
        this.canITeleportFromXToYQueryProcessor = canITeleportFromXToYQueryProcessor;
        this.loopPossibleFromXQueryProcessor = loopPossibleFromXQueryProcessor;
    }

    public void processQueries(String[] inputLines, Map<String, RouteNode> routeNodeGraphByCityName, Set<SimpleRoute> simpleRoutes) {

        System.out.println();
        System.out.println("Output:");
        citiesFromXInNJumpsQueryProcessor.processQueryLines(inputLines, routeNodeGraphByCityName);
        canITeleportFromXToYQueryProcessor.processQueryLines(inputLines, routeNodeGraphByCityName);
        loopPossibleFromXQueryProcessor.processQueryLines(inputLines, routeNodeGraphByCityName, simpleRoutes);
        System.out.println();
    }
}
