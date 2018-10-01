package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CitiesFromXInNJumpsQueryProcessorTest {

    public static final String TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY = "cities from city1 in 2 jumps";

    private RouteNode TEST_ROUTE_NODE =
            new RouteNode("city1", new ArrayList<String>(){{add("city1");}}, new ArrayList<RouteNode>(){{
                add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city2");}}, new ArrayList<RouteNode>(){{
                    add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city2");add("city3");}}, new ArrayList<>()));
                    add(new RouteNode("city4", new ArrayList<String>(){{add("city1");add("city2");add("city3");add("city4");}}, new ArrayList<>()));
                }}));
                add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city3");}}, new ArrayList<RouteNode>(){{
                    add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city3");add("city2");}}, new ArrayList<>()));
                }}));
            }});

    private Map<String, RouteNode> TEST_ROUTE_NODE_GRAPH = new HashMap<String, RouteNode>(){{put("city1", TEST_ROUTE_NODE);}};

    @InjectMocks
    private CitiesFromXInNJumpsQueryProcessor citiesFromXInNJumpsQueryProcessor;

    @Mock
    private QueryUtil queryUtil;

    @Test
    public void testFindCitiesNamesJumped_ValidInput() {

        CitiesFromXInNJumpsQuery citiesFromXInNJumpsQuery = new CitiesFromXInNJumpsQuery("city1", 2);
        when(queryUtil.parseCitiesFromXInNJumpsQuery(TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY)).thenReturn(citiesFromXInNJumpsQuery);

        Set<String> expectedResult = new HashSet<String>() {{
            add("city2");
            add("city3");
            add("city4");
        }};
        Set<String> result = citiesFromXInNJumpsQueryProcessor.findCitiesNamesJumped(TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY, TEST_ROUTE_NODE_GRAPH);

        assertEquals(expectedResult, result);
    }
}
