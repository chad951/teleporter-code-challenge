package com.cortek.solutions.teleportercodechallenge.util;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import com.cortek.solutions.teleportercodechallenge.route.SimpleRoute;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RoutePathUtilTest {

    private static Set<SimpleRoute> TEST_SIMPLE_ROUTES = new HashSet<SimpleRoute>() {{
        add(new SimpleRoute("city1", "city2"));
        add(new SimpleRoute("city3", "city1"));
        add(new SimpleRoute("city2", "city3"));
    }};

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private RoutePathUtil routePathUtil;

    @Test
    public void testBuildRoutePath_NullOriginCityName() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathUtil.NULL_ORIGIN_CITY_NAME_MESSAGE);

        routePathUtil.buildRoutePath(null, new SimpleRoute("", ""), new RoutePath(), new HashSet<>());
    }

    @Test
    public void testBuildRoutePath_NullCurrentSimpleRoute() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathUtil.NULL_CURRENT_SIMPLE_ROUTE_MESSAGE);

        routePathUtil.buildRoutePath("", null, new RoutePath(), new HashSet<>());
    }

    @Test
    public void testBuildRoutePath_NullRoutePath() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathUtil.NULL_ROUTE_PATH_MESSAGE);

        routePathUtil.buildRoutePath("", new SimpleRoute("", ""), null, new HashSet<>());
    }

    @Test
    public void testBuildRoutePath_NullSimpleRoutesSet() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathUtil.NULL_SIMPLE_ROUTE_SET_MESSAGE);

        routePathUtil.buildRoutePath("", new SimpleRoute("", ""), new RoutePath(), null);
    }

    @Test
    public void testBuildRoutePath_ValidInput() {

        RoutePath expectedResult = new RoutePath();
        expectedResult.getPath().add("city1");
        expectedResult.getPath().add("city2");
        expectedResult.getPath().add("city3");
        expectedResult.getIncludedSimpleRoutes().add(new SimpleRoute("city1", "city2"));
        expectedResult.getIncludedSimpleRoutes().add(new SimpleRoute("city2", "city3"));

        RoutePath result = routePathUtil.buildRoutePath("city1", new SimpleRoute("city1", "city2"), new RoutePath(), TEST_SIMPLE_ROUTES);

        assertEquals(expectedResult, result);
    }
}
