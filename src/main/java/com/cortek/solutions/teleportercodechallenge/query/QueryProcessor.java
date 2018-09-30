package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.util.QueryUtil;
import com.cortek.solutions.teleportercodechallenge.util.TeleporterInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class QueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    @Autowired
    public QueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
    }

    public void processQueries(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName) {

    }

    public void processCitiesFromXInNJumpsQueryLines(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName) {

        Set<String> citiesFromXInNJumpsQueryLines = teleporterInputUtil.extractCitiesFromXInNJumpsQueryLines(inputLines);

    }

    public Set<String> findCitiesFromXInNJumpsQueryLine(String citiesFromXInNJumpsQueryLine, Map<String, Set<RoutePath>> routePathsByCityName) {

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
