package com.cortek.solutions.teleportercodechallenge.route;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class SimpleRouteTest {

    private static final String INPUT_LINE_ROUTE = "Fortuna - Hemingway";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testEquals_DirectComparisonTrue() {

        String testCityName1 = "city1";
        String testCityName2 = "city2";

        SimpleRoute simpleRoute1 = new SimpleRoute(testCityName1, testCityName2);
        SimpleRoute simpleRoute2 = new SimpleRoute(testCityName1, testCityName2);

        assertTrue(simpleRoute1.equals(simpleRoute2));
    }

    @Test
    public void testEquals_InverseComparisonTrue() {

        String testCityName1 = "city1";
        String testCityName2 = "city2";

        SimpleRoute simpleRoute1 = new SimpleRoute(testCityName1, testCityName2);
        SimpleRoute simpleRoute2 = new SimpleRoute(testCityName2, testCityName1);

        assertTrue(simpleRoute1.equals(simpleRoute2));
    }

    @Test
    public void testEquals_NullValue() {

        String testCityName1 = "city1";
        String testCityName2 = "city2";

        SimpleRoute simpleRoute1 = new SimpleRoute(testCityName1, testCityName2);

        assertFalse(simpleRoute1.equals(null));
    }

    @Test
    public void testEquals_DifferentType() {

        String testCityName1 = "city1";
        String testCityName2 = "city2";

        SimpleRoute simpleRoute1 = new SimpleRoute(testCityName1, testCityName2);

        assertFalse(simpleRoute1.equals(""));
    }

    @Test
    public void testContainsCityName_NullCityName() {

        SimpleRoute simpleRoute = new SimpleRoute("city1", "city2");
        boolean result = simpleRoute.containsCityName(null);

        assertFalse(result);
    }

    @Test
    public void testContainsCityName_NonNullCityNameTrue() {

        SimpleRoute simpleRoute = new SimpleRoute("city1", "city2");
        boolean result = simpleRoute.containsCityName("city1");

        assertTrue(result);
    }

    @Test
    public void testContainsCityName_NonNullCityNameFalse() {

        SimpleRoute simpleRoute = new SimpleRoute("city1", "city2");
        boolean result = simpleRoute.containsCityName("city3");

        assertFalse(result);
    }

    @Test
    public void testCreateSimpleRoute_NullRouteInputLine() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(SimpleRoute.NULL_ROUTE_INPUT_LINE_MESSAGE);

        SimpleRoute.createSimpleRoute(null);
    }

    @Test
    public void testCreateSimpleRoute_BlankRouteInputLineNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(SimpleRoute.BLANK_ROUTE_INPUT_LINE_MESSAGE);

        SimpleRoute.createSimpleRoute("");
    }

    @Test
    public void testCreateSimpleRoute_BlankRouteInputLineWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(SimpleRoute.BLANK_ROUTE_INPUT_LINE_MESSAGE);

        SimpleRoute.createSimpleRoute(" ");
    }

    @Test
    public void testCreateSimpleRoute_ValidInput() {

        SimpleRoute expectedResult = new SimpleRoute("Fortuna", "Hemingway");

        SimpleRoute result = SimpleRoute.createSimpleRoute(INPUT_LINE_ROUTE);

        assertNotNull(result);
        assertEquals(expectedResult, result);
    }
}
