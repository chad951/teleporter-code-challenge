package com.cortek.solutions.teleportercodechallenge.util;

import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
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

import static org.junit.Assert.assertEquals;

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

}
