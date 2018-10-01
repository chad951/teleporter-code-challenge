package com.cortek.solutions.teleportercodechallenge.route;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class RouteNode {

    @NonNull
    private String cityName;

    @NonNull
    private List<String> teleportPath;

    @NonNull
    private List<RouteNode> directRouteNodes;
}
