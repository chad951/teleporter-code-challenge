package com.cortek.solutions.teleportercodechallenge.route;

import com.cortek.solutions.teleportercodechallenge.util.TeleporterInputUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RoutePathProcessorTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private RoutePathProcessor routePathProcessor;

    @Mock
    private TeleporterInputUtil teleporterInputUtil;

    @Test
    public void testCreateRouteMap_NullRouteSet() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(RoutePathProcessor.NULL_ROUTES_SET_MESSAGE);

        routePathProcessor.createRouteMap(null);
    }

    @Test
    public void testCreateRouteMap_EmptyRouteSet() {

        Map<String, String[]> result = routePathProcessor.createRouteMap(new HashSet<String>());

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    public void testCreateRouteMap_NonEmptyRouteSet() {

        String fortunaToHemingwayRoute = "Fortuna - Hemingway";
        String fortunaToAtlantisRoute = "Fortuna - Atlantis";
        String hemingwayToChesterfieldRoute = "Hemingway - Chesterfield";

        Set<String> routeSet = new HashSet<String>() {{
            add(fortunaToHemingwayRoute);
            add(fortunaToAtlantisRoute);
            add(hemingwayToChesterfieldRoute);
        }};

        when(teleporterInputUtil.parseRouteLineIntoCityNameArray(fortunaToHemingwayRoute)).thenReturn(new String[] {"Fortuna", "Hemingway"});
        when(teleporterInputUtil.parseRouteLineIntoCityNameArray(fortunaToAtlantisRoute)).thenReturn(new String[] {"Fortuna", "Atlantis"});
        when(teleporterInputUtil.parseRouteLineIntoCityNameArray(hemingwayToChesterfieldRoute)).thenReturn(new String[] {"Hemingway", "Chesterfield"});

        Map<String, String[]> result = routePathProcessor.createRouteMap(routeSet);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        System.out.println(result);
    }

}
