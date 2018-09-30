package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.util.QueryUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QueryProcessorTest {

    public static final String TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY = "cities from city1 in 2 jumps";

    private static final Map<String, Set<RoutePath>> TEST_ROUTE_PATHS_BY_CITY_NAME = new HashMap<String, Set<RoutePath>>() {{
        put("city1", new HashSet<RoutePath>() {{
            RoutePath routePath = new RoutePath();
            routePath.getPath().addAll(new ArrayList<String>() {{
                                                 add("city1");
                                                 add("city2");
                                                 add("city3");
                                             }});
            add(routePath);
        }});
    }};

    @InjectMocks
    private QueryProcessor queryProcessor;

    @Mock
    private QueryUtil queryUtil;

    @Test
    public void testFindCitiesFromXInNJumpsQueryLine_ValidInput() {

        CitiesFromXInNJumpsQuery citiesFromXInNJumpsQuery = new CitiesFromXInNJumpsQuery("city1", 2);
        when(queryUtil.parseCitiesFromXInNJumpsQuery(TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY)).thenReturn(citiesFromXInNJumpsQuery);

        Set<String> expectedResult = new HashSet<String>() {{
            add("city2");
            add("city3");
        }};
        Set<String> result = queryProcessor.findCitiesFromXInNJumpsQueryLine(TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY, TEST_ROUTE_PATHS_BY_CITY_NAME);

        assertEquals(expectedResult, result);
    }
}
