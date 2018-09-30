package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.util.RoutePathUtil;
import com.cortek.solutions.teleportercodechallenge.util.SimpleRouteUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoutePathProcessorTest {

    private static final Set<String> TEST_INPUT_ROUTES = new HashSet<String>() {{
            add("Fortuna - Hemingway");
            add("Fortuna - Atlantis");
    }};

    private static final Set<SimpleRoute> TEST_SIMPLE_ROUTES = new HashSet<SimpleRoute>() {{
        add(new SimpleRoute("Fortuna", "Hemingway"));
        add(new SimpleRoute("Fortuna", "Atlantis"));
    }};

    private static final Set<String> TEST_UNIQUE_CITY_NAMES = new HashSet<String>() {{
        add("Fortuna");
        add("Hemingway");
        add("Atlantis");
    }};

    Map<String, Set<SimpleRoute>> TEST_SIMPLE_ROUTES_BY_CITY_NAME = new HashMap<String, Set<SimpleRoute>>() {{
        put("Fortuna", new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Hemingway"));
            add(new SimpleRoute("Fortuna", "Atlantis"));
        }});
        put("Hemingway", new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Hemingway"));
        }});
        put("Atlantis", new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Atlantis"));
        }});
    }};

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private RoutePathProcessor routePathProcessor;

    @Mock
    private SimpleRouteUtil simpleRouteUtil;

    @Mock
    private RoutePathUtil routePathUtil;

    @Test
    public void testGenerateRoutePaths_NullInputRoutes() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathProcessor.NULL_INPUT_ROUTES_SET_MESSAGE);

        routePathProcessor.generateRoutePaths(null);
    }

    @Test
    public void testGenerateRoutePaths_EmptyInputRoutes() {

        Set<String> inputRoutes = new HashSet<>();
        Set<SimpleRoute> simpleRoutes = new HashSet<>();
        Set<String> uniqueCityNames = new HashSet<>();
        Map<String, Set<SimpleRoute>> simpleRoutesByCityNameMap = new HashMap<>();

        when(simpleRouteUtil.generateSimpleRoutesFromInput(inputRoutes)).thenReturn(simpleRoutes);
        when(simpleRouteUtil.extractUniqueCityNames(simpleRoutes)).thenReturn(uniqueCityNames);
        when(simpleRouteUtil.buildSimpleRoutesMapByCityNames(uniqueCityNames, simpleRoutes)).thenReturn(simpleRoutesByCityNameMap);

        Map<String, Set<RoutePath>> routePathsByCityName = routePathProcessor.generateRoutePaths(inputRoutes);

        assertNotNull(routePathsByCityName);
        assertTrue(routePathsByCityName.isEmpty());
    }

    @Test
    public void testGenerateRoutePaths_NonEmptyInputRoutes() {

        when(simpleRouteUtil.generateSimpleRoutesFromInput(TEST_INPUT_ROUTES)).thenReturn(TEST_SIMPLE_ROUTES);
        when(simpleRouteUtil.extractUniqueCityNames(TEST_SIMPLE_ROUTES)).thenReturn(TEST_UNIQUE_CITY_NAMES);
        when(simpleRouteUtil.buildSimpleRoutesMapByCityNames(TEST_UNIQUE_CITY_NAMES, TEST_SIMPLE_ROUTES)).thenReturn(TEST_SIMPLE_ROUTES_BY_CITY_NAME);
        RoutePath routePathFortunaToHemingway = new RoutePath();
        routePathFortunaToHemingway.getPath().addAll(new ArrayList<String>() {{
            add("Fortuna");
            add("Hemingway");
        }});
        routePathFortunaToHemingway.getIncludedSimpleRoutes().addAll(new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Hemingway"));
        }});
        when(routePathUtil.buildRoutePath("Fortuna", new SimpleRoute("Fortuna", "Hemingway"), new RoutePath(), TEST_SIMPLE_ROUTES)).thenReturn(routePathFortunaToHemingway);

        RoutePath routePathFortunaToAtlantis = new RoutePath();
        routePathFortunaToAtlantis.getPath().addAll(new ArrayList<String>() {{
            add("Fortuna");
            add("Atlantis");
        }});
        routePathFortunaToAtlantis.getIncludedSimpleRoutes().addAll(new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Atlantis"));
        }});
        when(routePathUtil.buildRoutePath("Fortuna", new SimpleRoute("Fortuna", "Atlantis"), new RoutePath(), TEST_SIMPLE_ROUTES)).thenReturn(routePathFortunaToAtlantis);

        RoutePath routePathHemingwayToFortuna = new RoutePath();
        routePathHemingwayToFortuna.getPath().addAll(new ArrayList<String>() {{
            add("Hemingway");
            add("Fortuna");
        }});
        routePathHemingwayToFortuna.getIncludedSimpleRoutes().addAll(new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Hemingway"));
        }});
        when(routePathUtil.buildRoutePath("Hemingway", new SimpleRoute("Fortuna", "Hemingway"), new RoutePath(), TEST_SIMPLE_ROUTES)).thenReturn(routePathHemingwayToFortuna);

        RoutePath routePathAtlantisToFortuna = new RoutePath();
        routePathAtlantisToFortuna.getPath().addAll(new ArrayList<String>() {{
            add("Atlantis");
            add("Fortuna");
        }});
        routePathAtlantisToFortuna.getIncludedSimpleRoutes().addAll(new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("Fortuna", "Atlantis"));
        }});
        when(routePathUtil.buildRoutePath("Atlantis", new SimpleRoute("Fortuna", "Atlantis"), new RoutePath(), TEST_SIMPLE_ROUTES)).thenReturn(routePathAtlantisToFortuna);

        Map<String, Set<RoutePath>> result = routePathProcessor.generateRoutePaths(TEST_INPUT_ROUTES);

        assertNotNull(result);
        assertEquals(3, result.size());
        assertEquals(new HashSet<RoutePath>() {{
            add(routePathFortunaToHemingway);
            add(routePathFortunaToAtlantis);
        }}, result.get("Fortuna"));
        assertEquals(new HashSet<RoutePath>() {{
            add(routePathHemingwayToFortuna);
        }}, result.get("Hemingway"));
        assertEquals(new HashSet<RoutePath>() {{
            add(routePathAtlantisToFortuna);
        }}, result.get("Atlantis"));
    }

}
