package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.query.CitiesFromXInNJumpsQuery;
import com.cortek.solutions.teleportercodechallenge.query.QueryUtil;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class QueryUtilTest {

    public static final String TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY = "cities from Summerton in 1 jumps";

    public static final String TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY = "can I teleport from Springton to Atlantis";

    public static final String TEST_LOOP_POSSIBLE_FROM_X_QUERY = "loop possible from Oaktown";

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private QueryUtil queryUtil;

    @Test
    public void testParseCitiesFromXInNJumpsQuery_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(QueryUtil.NULL_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);

        queryUtil.parseCitiesFromXInNJumpsQuery(null);
    }

    @Test
    public void testParseCitiesFromXInNJumpsQuery_EmptyInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(QueryUtil.EMPTY_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);

        queryUtil.parseCitiesFromXInNJumpsQuery("");
    }

    @Test
    public void testParseCitiesFromXInNJumpsQuery_EmptyInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(QueryUtil.EMPTY_CITIES_FROM_X_IN_N_JUMPS_QUERY_MESSAGE);

        queryUtil.parseCitiesFromXInNJumpsQuery(" ");
    }

    @Test
    public void testParseCitiesFromXInNJumpsQuery_ValidInput() {

        CitiesFromXInNJumpsQuery expectedResult = new CitiesFromXInNJumpsQuery("Summerton", 1);

        CitiesFromXInNJumpsQuery result = queryUtil.parseCitiesFromXInNJumpsQuery(TEST_CITIES_FROM_X_IN_N_JUMPS_QUERY);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testParseCanITeleportFromXToYQuery_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(QueryUtil.NULL_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE);

        queryUtil.parseCanITeleportFromXToYQuery(null);
    }

    @Test
    public void testParseCanITeleportFromXToYQuery_EmptyInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(QueryUtil.EMPTY_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE);

        queryUtil.parseCanITeleportFromXToYQuery("");
    }

    @Test
    public void testParseCanITeleportFromXToYQuery_EmptyInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(QueryUtil.EMPTY_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY_MESSAGE);

        queryUtil.parseCanITeleportFromXToYQuery(" ");
    }

    @Test
    public void testParseCanITeleportFromXToYQuery_ValidInput() {

        CanITeleportFromXToYQuery expectedResult = new CanITeleportFromXToYQuery("Springton", "Atlantis");

        CanITeleportFromXToYQuery result = queryUtil.parseCanITeleportFromXToYQuery(TEST_CAN_I_TELEPORT_FROM_X_TO_Y_QUERY);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testParseLoopPossibleFromXQuery_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(QueryUtil.NULL_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE);

        queryUtil.parseLoopPossibleFromXQuery(null);
    }

    @Test
    public void testParseLoopPossibleFromXQuery_EmptyInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(QueryUtil.EMPTY_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE);

        queryUtil.parseLoopPossibleFromXQuery("");
    }

    @Test
    public void testParseLoopPossibleFromXQuery_EmptyInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(QueryUtil.EMPTY_LOOP_POSSIBLE_FROM_X_QUERY_MESSAGE);

        queryUtil.parseLoopPossibleFromXQuery(" ");
    }

    @Test
    public void testParseLoopPossibleFromXQuery_ValidInput() {

        LoopPossibleFromXQuery expectedResult = new LoopPossibleFromXQuery("Oaktown");

        LoopPossibleFromXQuery result = queryUtil.parseLoopPossibleFromXQuery(TEST_LOOP_POSSIBLE_FROM_X_QUERY);

        assertEquals(expectedResult, result);
    }
}
