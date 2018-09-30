package com.cortek.solutions.teleportercodechallenge.route;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RoutePathProcessorTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private RoutePathProcessor routePathProcessor;

    @Test
    public void testGenerateSimpleRoutesFromInput_NullRouteSet() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathProcessor.NULL_ROUTES_SET_MESSAGE);

        routePathProcessor.generateSimpleRoutesFromInput(null);
    }

    @Test
    public void testGenerateSimpleRoutesFromInput_EmptyRouteSet() {

        Set<SimpleRoute> result = routePathProcessor.generateSimpleRoutesFromInput(new HashSet<>());

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

        Set<SimpleRoute> result = routePathProcessor.generateSimpleRoutesFromInput(routeSet);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.contains(fortunaToHemingwaySimpleRoute));
        assertTrue(result.contains(fortunaToAtlantisSimpleRoute));
        assertTrue(result.contains(hemingwayToChesterfieldSimpleRoute));
    }

    @Test
    public void testExtractUniqueCityNames_EmptyRouteArraySet() {

        Set<String> result = routePathProcessor.extractUniqueCityNames(new HashSet<>());

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

        Set<String> result = routePathProcessor.extractUniqueCityNames(simpleRoutes);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(3, result.size());
        assertTrue(result.contains(cityNameFortuna));
        assertTrue(result.contains(cityNameHemingway));
        assertTrue(result.contains(cityNameAtlantis));

    }

}
