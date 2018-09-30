package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.output.TeleporterOutputProcessor;
import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputUtil;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class CanITeleportFromXToYQueryProcessor implements QueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    private TeleporterOutputProcessor teleporterOutputProcessor;

    public CanITeleportFromXToYQueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil, TeleporterOutputProcessor teleporterOutputProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
        this.teleporterOutputProcessor = teleporterOutputProcessor;
    }

    @Override
    public void processQueryLines(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName) {

        Set<String> canITeleportFromXToYQueryLines = teleporterInputUtil.extractCanITeleportFromXToYQueryLines(inputLines);

        canITeleportFromXToYQueryLines.forEach(citiesFromXInNJumpsQueryLine -> {
            boolean answer = canTeleportFromXToY(citiesFromXInNJumpsQueryLine, routePathsByCityName);
            teleporterOutputProcessor.printCanITeleportFromXToYQueryLineResults(citiesFromXInNJumpsQueryLine, answer);
        });
    }

    public boolean canTeleportFromXToY(String canITeleportFromXToYQueryLine, Map<String, Set<RoutePath>> routePathsByCityName) {

        CanITeleportFromXToYQuery canITeleportFromXToYQuery = queryUtil.parseCanITeleportFromXToYQuery(canITeleportFromXToYQueryLine);

        Set<RoutePath> routePaths = routePathsByCityName.get(canITeleportFromXToYQuery.getTeleportOriginCityName());

        return routePaths.stream().anyMatch(routePath -> routePath.getPath().contains(canITeleportFromXToYQuery.getTeleportDestinationCityName()));
    }
}
