package com.cortek.solutions.teleportercodechallenge.input;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class TeleporterInputUtilTest {

    private static final String INPUT_LINE_CAN_I_TELEPORT_FROM_X_TO_Y = "can I teleport from Springton to Atlantis";

    private static final String INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS = "cities from Summerton in 1 jumps";

    private static final String INPUT_LINE_IS_LOOP_POSSIBLE_FROM_X = "loop possible from Oaktown";

    private static final String INPUT_LINE_ROUTE = "Fortuna - Hemingway";

    private static final String[] TEST_INPUT_LINES_ARRAY = new String[] {
            "Fortuna - Hemingway",
            "Fortuna - Atlantis",
            "cities from Summerton in 1 jumps",
            "cities from Summerton in 2 jumps",
            "can I teleport from Springton to Atlantis",
            "can I teleport from Oaktown to Atlantis",
            "loop possible from Oaktown",
            "loop possible from Fortuna"};

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @InjectMocks
    private TeleporterInputUtil teleporterInputUtil;

    @Test
    public void testIsRouteLine_NonNullOrBlankValidInput() {

        boolean result = teleporterInputUtil.isRouteLine(INPUT_LINE_ROUTE);

        assertTrue(result);
    }

    @Test
    public void testIsRouteLine_NonNullOrBlankInValidInput() {

        boolean result = teleporterInputUtil.isRouteLine("cities from Summerton in 1 jumps");

        assertFalse(result);
    }

    @Test
    public void testIsRouteLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isRouteLine(null);
    }

    @Test
    public void testIsRouteLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isRouteLine("");
    }

    @Test
    public void testIsRouteLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isRouteLine(" ");
    }

    @Test()
    public void testExtractRouteLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractRouteLines(null);
    }

    @Test()
    public void testExtractRouteLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractRouteLines(new String[0]);
    }


    @Test()
    public void testExtractRouteLines_ValidInput() {

        List<String> expectedResult = new ArrayList<String>() {{
            add("Fortuna - Hemingway");
            add("Fortuna - Atlantis");
        }};

        List<String> result = teleporterInputUtil.extractRouteLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsCitiesFromXInNJumpsQueryLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCitiesFromXInNJumpsQueryLine(null);
    }

    @Test
    public void testIsCitiesFromXInNJumpsQueryLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCitiesFromXInNJumpsQueryLine("");
    }

    @Test
    public void testIsCitiesFromXInNJumpsQueryLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCitiesFromXInNJumpsQueryLine(" ");
    }

    @Test
    public void testIsCitiesFromXInNJumpsQueryLine_ValidInputTrue() {

        boolean result = teleporterInputUtil.isCitiesFromXInNJumpsQueryLine(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS);

        assertTrue(result);
    }

    @Test
    public void testIsCitiesFromXInNJumpsQueryLine_ValidInputFalse() {

        boolean result = teleporterInputUtil.isCitiesFromXInNJumpsQueryLine(INPUT_LINE_CAN_I_TELEPORT_FROM_X_TO_Y);

        assertFalse(result);
    }

    @Test()
    public void testExtractCitiesFromXInNJumpsQueryLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCitiesFromXInNJumpsQueryLines(null);
    }

    @Test()
    public void testExtractCitiesFromXInNJumpsQueryLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCitiesFromXInNJumpsQueryLines(new String[0]);
    }

    @Test()
    public void testExtractCitiesFromXInNJumpsQueryLines_ValidInput() {

        List<String> expectedResult = new ArrayList<String>() {{
            add("cities from Summerton in 1 jumps");
            add("cities from Summerton in 2 jumps");
        }};

        List<String> result = teleporterInputUtil.extractCitiesFromXInNJumpsQueryLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsCanITeleportFromXToYQueryLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCanITeleportFromXToYQueryLine(null);
    }

    @Test
    public void testIsCanITeleportFromXToYQueryLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCanITeleportFromXToYQueryLine("");
    }

    @Test
    public void testIsCanITeleportFromXToYQueryLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCanITeleportFromXToYQueryLine(" ");
    }

    @Test
    public void testIsCanITeleportFromXToYQueryLine_ValidInputTrue() {

        boolean result = teleporterInputUtil.isCanITeleportFromXToYQueryLine(INPUT_LINE_CAN_I_TELEPORT_FROM_X_TO_Y);

        assertTrue(result);
    }

    @Test
    public void testIsCanITeleportFromXToYQueryLine_ValidInputFalse() {

        boolean result = teleporterInputUtil.isCanITeleportFromXToYQueryLine(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS);

        assertFalse(result);
    }

    @Test()
    public void testExtractCanITeleportFromXToYQueryLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCanITeleportFromXToYQueryLines(null);
    }

    @Test()
    public void testExtractCanITeleportFromXToYQueryLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCanITeleportFromXToYQueryLines(new String[0]);
    }

    @Test()
    public void testExtractCanITeleportFromXToYQueryLines_ValidInput() {

        List<String> expectedResult = new ArrayList<String>() {{
            add("can I teleport from Springton to Atlantis");
            add("can I teleport from Oaktown to Atlantis");
        }};

        List<String> result = teleporterInputUtil.extractCanITeleportFromXToYQueryLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(expectedResult, result);
    }

    @Test
    public void testIsLoopPossiblefromXQueryLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isLoopPossiblefromXQueryLine(null);
    }

    @Test
    public void testIsLoopPossiblefromXQueryLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isLoopPossiblefromXQueryLine("");
    }

    @Test
    public void testIsLoopPossiblefromXQueryLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isLoopPossiblefromXQueryLine(" ");
    }

    @Test
    public void testIsLoopPossiblefromXQueryLine_ValidInputTrue() {

        boolean result = teleporterInputUtil.isLoopPossiblefromXQueryLine(INPUT_LINE_IS_LOOP_POSSIBLE_FROM_X);

        assertTrue(result);
    }

    @Test
    public void testIsLoopPossiblefromXQueryLine_ValidInputFalse() {

        boolean result = teleporterInputUtil.isLoopPossiblefromXQueryLine(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS);

        assertFalse(result);
    }

    @Test()
    public void testExtractLoopPossiblefromXQueryLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractLoopPossiblefromXQueryLines(null);
    }

    @Test()
    public void testExtractLoopPossiblefromXQueryLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractLoopPossiblefromXQueryLines(new String[0]);
    }

    @Test()
    public void testExtractLoopPossiblefromXQueryLines_ValidInput() {

        List<String> expectedResult = new ArrayList<String>() {{
            add("loop possible from Oaktown");
            add("loop possible from Fortuna");
        }};

        List<String> result = teleporterInputUtil.extractLoopPossiblefromXQueryLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(expectedResult, result);
    }

}
