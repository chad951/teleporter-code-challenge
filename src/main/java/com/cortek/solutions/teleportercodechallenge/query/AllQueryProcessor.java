package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class AllQueryProcessor {

    private CitiesFromXInNJumpsQueryProcessor citiesFromXInNJumpsQueryProcessor;

    private CanITeleportFromXToYQueryProcessor canITeleportFromXToYQueryProcessor;

    @Autowired
    public AllQueryProcessor(CitiesFromXInNJumpsQueryProcessor citiesFromXInNJumpsQueryProcessor, CanITeleportFromXToYQueryProcessor canITeleportFromXToYQueryProcessor) {
        this.citiesFromXInNJumpsQueryProcessor = citiesFromXInNJumpsQueryProcessor;
        this.canITeleportFromXToYQueryProcessor = canITeleportFromXToYQueryProcessor;
    }

    public void processQueries(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName) {

        citiesFromXInNJumpsQueryProcessor.processQueryLines(inputLines, routePathsByCityName);
        canITeleportFromXToYQueryProcessor.processQueryLines(inputLines, routePathsByCityName);
    }
}
