package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.output.TeleporterOutputProcessor;
import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class CitiesFromXInNJumpsQueryProcessor implements QueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    private TeleporterOutputProcessor teleporterOutputProcessor;

    @Autowired
    public CitiesFromXInNJumpsQueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil, TeleporterOutputProcessor teleporterOutputProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
        this.teleporterOutputProcessor = teleporterOutputProcessor;
    }

    @Override
    public void processQueryLines(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName) {

        Set<String> citiesFromXInNJumpsQueryLines = teleporterInputUtil.extractCitiesFromXInNJumpsQueryLines(inputLines);

        citiesFromXInNJumpsQueryLines.forEach(citiesFromXInNJumpsQueryLine -> {
            Set<String> cityNamesJumped = findCitiesNamesJumped(citiesFromXInNJumpsQueryLine, routePathsByCityName);
            teleporterOutputProcessor.printCitiesFromXInNJumpsQueryLineResults(citiesFromXInNJumpsQueryLine, cityNamesJumped);
        });
    }

    public Set<String> findCitiesNamesJumped(String citiesFromXInNJumpsQueryLine, Map<String, Set<RoutePath>> routePathsByCityName) {

        Set<String> cityNamesJumped = new HashSet<>();
        CitiesFromXInNJumpsQuery citiesFromXInNJumpsQuery = queryUtil.parseCitiesFromXInNJumpsQuery(citiesFromXInNJumpsQueryLine);
        Set<RoutePath> routePaths = routePathsByCityName.get(citiesFromXInNJumpsQuery.getOriginCityName());
        Integer maxNumberOfJumps = citiesFromXInNJumpsQuery.getMaxNumberOfJumps();

        routePaths.forEach(routePath -> {
            List<String> path = routePath.getPath();

            String originCityName = path.get(0);
            // Start at 1 so we do not include the origin city.
            for (int i = 1; (i < path.size() && i <= maxNumberOfJumps); i++) {
                // The following logic is needed to prevent adding the loop back city to this jump list.
                String currentCityName = path.get(i);

                if (!currentCityName.equals(originCityName)) {
                    cityNamesJumped.add(currentCityName);
                }
            }
        });

        return cityNamesJumped;
    }
}
