package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoopPossibleFromXQueryProcessorTest {

    public static final String TEST_LOOP_POSSIBLE_FROM_X_QUERY_TRUE = "loop possible from city1";

    public static final String TEST_LOOP_POSSIBLE_FROM_X_QUERY_FALSE = "loop possible from city2";

    private static Set<SimpleRoute> TEST_SIMPLE_ROUTES = new HashSet<SimpleRoute>() {{
        add(new SimpleRoute("city1", "city2"));
        add(new SimpleRoute("city3", "city1"));
        add(new SimpleRoute("city2", "city3"));
    }};

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

    @InjectMocks
    private LoopPossibleFromXQueryProcessor loopPossibleFromXQueryProcessor;

    @Mock
    private QueryUtil queryUtil;

    @Test
    public void testLoopPossibleFromX_True() {

        when(queryUtil.parseLoopPossibleFromXQuery(TEST_LOOP_POSSIBLE_FROM_X_QUERY_TRUE)).thenReturn(new LoopPossibleFromXQuery("city1"));

        boolean result = loopPossibleFromXQueryProcessor.isLoopPossible(TEST_LOOP_POSSIBLE_FROM_X_QUERY_TRUE, TEST_ROUTE_NODE_GRAPH, TEST_SIMPLE_ROUTES);

        assertTrue(result);
    }

    @Test
    public void testLoopPossibleFromX_False() {

        when(queryUtil.parseLoopPossibleFromXQuery(TEST_LOOP_POSSIBLE_FROM_X_QUERY_FALSE)).thenReturn(new LoopPossibleFromXQuery("city2"));

        boolean result = loopPossibleFromXQueryProcessor.isLoopPossible(TEST_LOOP_POSSIBLE_FROM_X_QUERY_FALSE, TEST_ROUTE_NODE_GRAPH, TEST_SIMPLE_ROUTES);

        assertFalse(result);
    }

    @Test
    public void testFindAllEdgeNodesForGivenCityName_ValidInput() {

        Set<RouteNode> expectedResults = new HashSet<RouteNode>(){{
            add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city2");add("city3");}}, new ArrayList<>()));
            add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city3");add("city2");}}, new ArrayList<>()));
        }};

        Set<RouteNode> result = loopPossibleFromXQueryProcessor.findAllEdgeNodesForGivenCityName(TEST_ROUTE_NODE, new HashSet<>());

        assertEquals(expectedResults, result);
    }
}
