package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.route.RouteNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CanITeleportFromXToYQueryProcessorTest {

    public static final String TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_TRUE = "can I teleport from city1 to city2";

    public static final String TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_FALSE = "can I teleport from city1 to city3";

    private RouteNode TEST_ROUTE_NODE =
            new RouteNode("city1", new ArrayList<String>(){{add("city1");}}, new ArrayList<RouteNode>(){{
                add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city2");}}, new ArrayList<RouteNode>(){{
                    add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city2");add("city3");}}, new ArrayList<>()));
                    add(new RouteNode("city4", new ArrayList<String>(){{add("city1");add("city2");add("city3");add("city4");}}, new ArrayList<>()));
                }}));
                add(new RouteNode("city3", new ArrayList<String>(){{add("city1");add("city3");}}, new ArrayList<RouteNode>(){{
                    add(new RouteNode("city2", new ArrayList<String>(){{add("city1");add("city3");add("city2");}}, new ArrayList<>()));
                    add(new RouteNode("city5", new ArrayList<String>(){{add("city1");add("city3");add("city2");add("city5");}}, new ArrayList<>()));
                }}));
            }});

    private Map<String, RouteNode> TEST_ROUTE_NODE_GRAPH = new HashMap<String, RouteNode>(){{put("city1", TEST_ROUTE_NODE);}};

    @InjectMocks
    private CanITeleportFromXToYQueryProcessor canITeleportFromXToYQueryProcessor;

    @Mock
    private QueryUtil queryUtil;

    @Test
    public void testCanTeleportFromXToY_True() {

        when(queryUtil.parseCanITeleportFromXToYQuery(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_TRUE)).thenReturn(new CanITeleportFromXToYQuery("city1", "city2"));

        boolean result = canITeleportFromXToYQueryProcessor.canTeleportFromXToY(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_TRUE, TEST_ROUTE_NODE_GRAPH);

        assertTrue(result);
    }

    @Test
    public void testCanTeleportFromXToY_False() {

        when(queryUtil.parseCanITeleportFromXToYQuery(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_FALSE)).thenReturn(new CanITeleportFromXToYQuery("city1", "city6"));

        boolean result = canITeleportFromXToYQueryProcessor.canTeleportFromXToY(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_FALSE, TEST_ROUTE_NODE_GRAPH);

        assertFalse(result);
    }
}
