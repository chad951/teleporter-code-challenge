package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import com.cortek.solutions.teleportercodechallenge.route.SimpleRouteUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SimpleRouteUtilTest {

    private static Set<SimpleRoute> TEST_SIMPLE_ROUTES = new HashSet<SimpleRoute>() {{
        add(new SimpleRoute("city1", "city2"));
        add(new SimpleRoute("city3", "city1"));
        add(new SimpleRoute("city2", "city3"));
    }};

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private SimpleRouteUtil simpleRouteUtil;

    @Test
    public void testGenerateSimpleRoutesFromInput_NullRouteSet() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(SimpleRouteUtil.NULL_ROUTES_SET_MESSAGE);

        simpleRouteUtil.generateSimpleRoutesFromInput(null);
    }

    @Test
    public void testGenerateSimpleRoutesFromInput_EmptyRouteSet() {

        Set<SimpleRoute> result = simpleRouteUtil.generateSimpleRoutesFromInput(new HashSet<>());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testGenerateSimpleRoutesFromInput_NonEmptyRouteSet() {

        String fortunaToHemingwayRoute = "Fortuna - Hemingway";
        String fortunaToAtlantisRoute = "Fortuna - Atlantis";
        String hemingwayToChesterfieldRoute = "Hemingway - Chesterfield";

        SimpleRoute fortunaToHemingwaySimpleRoute = new SimpleRoute("Fortuna", "Hemingway");
        SimpleRoute fortunaToAtlantisSimpleRoute = new SimpleRoute("Fortuna", "Atlantis");
        SimpleRoute hemingwayToChesterfieldSimpleRoute = new SimpleRoute("Hemingway", "Chesterfield");

        Set<String> routeSet = new HashSet<String>() {{
            add(fortunaToHemingwayRoute);
            add(fortunaToAtlantisRoute);
            add(hemingwayToChesterfieldRoute);
        }};

        Set<SimpleRoute> result = simpleRouteUtil.generateSimpleRoutesFromInput(routeSet);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.contains(fortunaToHemingwaySimpleRoute));
        assertTrue(result.contains(fortunaToAtlantisSimpleRoute));
        assertTrue(result.contains(hemingwayToChesterfieldSimpleRoute));
    }

    @Test
    public void testFindAllSimpleRoutesForCityName_NullCityName() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(SimpleRouteUtil.NULL_CITY_NAME_MESSAGE);

        simpleRouteUtil.findAllSimpleRoutesForCityName(null, new HashSet<>());
    }

    @Test
    public void testFindAllSimpleRoutesForCityName_NullSimpleRoutes() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(SimpleRouteUtil.NULL_SIMPLE_ROUTES_MESSAGE);

        simpleRouteUtil.findAllSimpleRoutesForCityName("city1", null);
    }

    @Test
    public void testFindAllSimpleRoutesForCityName_ValidInput() {

        Set<SimpleRoute> expectedResult = new HashSet<SimpleRoute>() {{
            add(new SimpleRoute("city1", "city2"));
            add(new SimpleRoute("city3", "city1"));
        }};

        Set<SimpleRoute> result = simpleRouteUtil.findAllSimpleRoutesForCityName("city1", TEST_SIMPLE_ROUTES);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testBuildSimpleRoutesMapByCityNames_NullCityNameSet() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(SimpleRouteUtil.NULL_CITY_NAME_SET_MESSAGE);

        simpleRouteUtil.buildSimpleRoutesMapByCityNames(null, new HashSet<>());
    }

    @Test
    public void testBuildSimpleRoutesMapByCityNames_NullSimpleRoutes() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(SimpleRouteUtil.NULL_SIMPLE_ROUTES_MESSAGE);

        simpleRouteUtil.buildSimpleRoutesMapByCityNames(new HashSet<>(), null);
    }

    @Test
    public void testBuildSimpleRoutesMapByCityNames_ValidInput() {

        Set<String> cityNames = new HashSet<String>() {{
            add("city1");
            add("city2");
        }};

        Map<String, Set<SimpleRoute>> expectedResult = new HashMap<String, Set<SimpleRoute>>() {{
            put("city1", new HashSet<SimpleRoute>() {{
                add(new SimpleRoute("city1", "city2"));
                add(new SimpleRoute("city3", "city1"));
            }});
            put("city2", new HashSet<SimpleRoute>() {{
                add(new SimpleRoute("city1", "city2"));
                add(new SimpleRoute("city2", "city3"));
            }});
        }};

        Map<String, Set<SimpleRoute>> result = simpleRouteUtil.buildSimpleRoutesMapByCityNames(cityNames, TEST_SIMPLE_ROUTES);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testExtractUniqueCityNames_EmptyRouteArraySet() {

        Set<String> result = simpleRouteUtil.extractUniqueCityNames(new HashSet<>());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testExtractUniqueCityNames_NonEmptyRouteArraySet() {

        String cityNameFortuna = "Fortuna";
        String cityNameHemingway = "Hemingway";
        String cityNameAtlantis = "Atlantis";

        Set<SimpleRoute> simpleRoutes = new HashSet<SimpleRoute>() {{
            add(new SimpleRoute(cityNameFortuna, cityNameHemingway));
            add(new SimpleRoute(cityNameFortuna, cityNameAtlantis));
        }};

        Set<String> result = simpleRouteUtil.extractUniqueCityNames(simpleRoutes);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.contains(cityNameFortuna));
        assertTrue(result.contains(cityNameHemingway));
        assertTrue(result.contains(cityNameAtlantis));
    }

}
