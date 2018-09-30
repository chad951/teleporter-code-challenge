package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;

import java.util.Map;
import java.util.Set;

public interface QueryProcessor {

    void processQueryLines(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName);
}
