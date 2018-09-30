package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CanITeleportFromXToYQueryProcessorTest {

    public static final String TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_TRUE = "can I teleport from city1 to city2";

    public static final String TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_FALSE = "can I teleport from city1 to city3";

    private static final Map<String, Set<RoutePath>> TEST_ROUTE_PATHS_BY_CITY_NAME = new HashMap<String, Set<RoutePath>>() {{
        put("city1", new HashSet<RoutePath>() {{
            RoutePath routePath = new RoutePath();
            routePath.getPath().addAll(new ArrayList<String>() {{
                add("city1");
                add("city2");
            }});
            add(routePath);
        }});
    }};

    @InjectMocks
    private CanITeleportFromXToYQueryProcessor canITeleportFromXToYQueryProcessor;

    @Mock
    private QueryUtil queryUtil;

    @Test
    public void testCanTeleportFromXToY_True() {

        when(queryUtil.parseCanITeleportFromXToYQuery(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_TRUE)).thenReturn(new CanITeleportFromXToYQuery("city1", "city2"));

        boolean result = canITeleportFromXToYQueryProcessor.canTeleportFromXToY(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_TRUE, TEST_ROUTE_PATHS_BY_CITY_NAME);

        assertTrue(result);
    }

    @Test
    public void testCanTeleportFromXToY_False() {

        when(queryUtil.parseCanITeleportFromXToYQuery(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_FALSE)).thenReturn(new CanITeleportFromXToYQuery("city1", "city3"));

        boolean result = canITeleportFromXToYQueryProcessor.canTeleportFromXToY(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_FALSE, TEST_ROUTE_PATHS_BY_CITY_NAME);

        assertFalse(result);
    }
}
