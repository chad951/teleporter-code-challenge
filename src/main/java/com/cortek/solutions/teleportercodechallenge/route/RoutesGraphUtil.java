package com.cortek.solutions.teleportercodechallenge.route;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class RoutesGraphUtil {

    public Map<String, RouteNode> buildRouteNodeGraphByCityName(Set<String> uniqueCityNames, final Set<SimpleRoute> simpleRoutes) {

        Map<String, RouteNode> routeNodeGraphByCityName = new HashMap<String, RouteNode>();

        uniqueCityNames.forEach(uniqueCityName -> {
            routeNodeGraphByCityName.put(uniqueCityName, buildRouteNodeforCityName(uniqueCityName, null, simpleRoutes));
        });

        return routeNodeGraphByCityName;
    }

    public RouteNode buildRouteNodeforCityName(String cityName, RouteNode previousRouteNode, final Set<SimpleRoute> simpleRoutes) {

        List<String> currentTeleportPath;

        if (previousRouteNode == null) {
            currentTeleportPath = new ArrayList<String>(){{add(cityName);}};
        } else {
            currentTeleportPath = new ArrayList<String>(){{addAll(previousRouteNode.getTeleportPath());}};
            currentTeleportPath.add(cityName);
        }

        RouteNode routeNode = new RouteNode(cityName, currentTeleportPath, new ArrayList<>());

        simpleRoutes.forEach(simpleRoute -> {
            if (simpleRoute.containsCityName(cityName)) {
                String directTeleportCityName = simpleRoute.findDirectTeleportCityName(cityName);
                if (!currentTeleportPath.contains(directTeleportCityName)) {
                    routeNode.getDirectRouteNodes().add(buildRouteNodeforCityName(directTeleportCityName, routeNode, simpleRoutes));
                }
            }
        });

        return routeNode;
    }

}
