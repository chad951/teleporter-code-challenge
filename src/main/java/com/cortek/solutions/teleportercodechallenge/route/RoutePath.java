package com.cortek.solutions.teleportercodechallenge.route;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class RoutePath {

    private List<String> path = new ArrayList<>();

    private List<SimpleRoute> includedSimpleRoutes = new ArrayList<>();
}
