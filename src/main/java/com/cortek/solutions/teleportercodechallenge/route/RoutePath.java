package com.cortek.solutions.teleportercodechallenge.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class RoutePath {

    private List<String> path = new ArrayList<>();

    private Set<SimpleRoute> includedSimpleRoutes = new HashSet<>();
}
