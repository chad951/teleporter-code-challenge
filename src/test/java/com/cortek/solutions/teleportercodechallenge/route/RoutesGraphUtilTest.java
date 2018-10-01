package com.cortek.solutions.teleportercodechallenge.route;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RoutesGraphUtilTest {

    private RouteNode TEST_ROUTE_NODE =
            new RouteNode("city1", new ArrayList<String>(){{add("city1");}}, new ArrayList<RouteNode>(){{
                add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city2");}}, new ArrayList<RouteNode>(){{
                    add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city2");add("city3");}}, new ArrayList<>()));
                }}));
                add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city3");}}, new ArrayList<RouteNode>(){{
                    add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city3");add("city2");}}, new ArrayList<>()));
                }}));
            }});

    private Map<String, RouteNode> TEST_ROUTE_NODE_GRAPH = new HashMap<String, RouteNode>(){{put("city1", TEST_ROUTE_NODE);}};

    private static Set<SimpleRoute> TEST_SIMPLE_ROUTES = new HashSet<SimpleRoute>() {{
        add(new SimpleRoute("city1", "city2"));
        add(new SimpleRoute("city3", "city1"));
        add(new SimpleRoute("city2", "city3"));
        add(new SimpleRoute("city4", "city5"));
        add(new SimpleRoute("city5", "city6"));
        add(new SimpleRoute("city7", "city8"));
    }};

    @InjectMocks
    private RoutesGraphUtil routesGraphUtil;

    @Test
    public void testBuildJumpNodeforCityName_ValidInput() {

        RouteNode result = routesGraphUtil.buildRouteNodeforCityName("city1", null, TEST_SIMPLE_ROUTES);

        assertEquals(TEST_ROUTE_NODE, result);
    }

    @Test
    public void testBuildRouteNodeGraphByCityName_ValidInput() {

        Map<String, RouteNode> result = routesGraphUtil.buildRouteNodeGraphByCityName(new HashSet<String>(){{add("city1");}}, TEST_SIMPLE_ROUTES);

        assertEquals(TEST_ROUTE_NODE_GRAPH, result);
    }

}
