package com.cortek.solutions.teleportercodechallenge.util;

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

        Set<String> expectedResult = new HashSet<String>() {{
            add("Fortuna - Hemingway");
            add("Fortuna - Atlantis");
        }};

        Set<String> result = teleporterInputUtil.extractRouteLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(result, expectedResult);
    }

    @Test
    public void testIsCitiesFromXInNJumpsQuestionLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCitiesFromXInNJumpsQuestionLine(null);
    }

    @Test
    public void testIsCitiesFromXInNJumpsQuestionLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCitiesFromXInNJumpsQuestionLine("");
    }

    @Test
    public void testIsCitiesFromXInNJumpsQuestionLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCitiesFromXInNJumpsQuestionLine(" ");
    }

    @Test
    public void testIsCitiesFromXInNJumpsQuestionLine_ValidInputTrue() {

        boolean result = teleporterInputUtil.isCitiesFromXInNJumpsQuestionLine(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS);

        assertTrue(result);
    }

    @Test
    public void testIsCitiesFromXInNJumpsQuestionLine_ValidInputFalse() {

        boolean result = teleporterInputUtil.isCitiesFromXInNJumpsQuestionLine(INPUT_LINE_CAN_I_TELEPORT_FROM_X_TO_Y);

        assertFalse(result);
    }

    @Test()
    public void testExtractCitiesFromXInNJumpsQuestionLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCitiesFromXInNJumpsQuestionLines(null);
    }

    @Test()
    public void testExtractCitiesFromXInNJumpsQuestionLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCitiesFromXInNJumpsQuestionLines(new String[0]);
    }

    @Test()
    public void testExtractCitiesFromXInNJumpsQuestionLines_ValidInput() {

        Set<String> expectedResult = new HashSet<String>() {{
            add("cities from Summerton in 1 jumps");
            add("cities from Summerton in 2 jumps");
        }};

        Set<String> result = teleporterInputUtil.extractCitiesFromXInNJumpsQuestionLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(result, expectedResult);
    }

    @Test
    public void testIsCanITeleportFromXToYQuestionLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCanITeleportFromXToYQuestionLine(null);
    }

    @Test
    public void testIsCanITeleportFromXToYQuestionLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCanITeleportFromXToYQuestionLine("");
    }

    @Test
    public void testIsCanITeleportFromXToYQuestionLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isCanITeleportFromXToYQuestionLine(" ");
    }

    @Test
    public void testIsCanITeleportFromXToYQuestionLine_ValidInputTrue() {

        boolean result = teleporterInputUtil.isCanITeleportFromXToYQuestionLine(INPUT_LINE_CAN_I_TELEPORT_FROM_X_TO_Y);

        assertTrue(result);
    }

    @Test
    public void testIsCanITeleportFromXToYQuestionLine_ValidInputFalse() {

        boolean result = teleporterInputUtil.isCanITeleportFromXToYQuestionLine(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS);

        assertFalse(result);
    }

    @Test()
    public void testExtractCanITeleportFromXToYQuestionLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCanITeleportFromXToYQuestionLines(null);
    }

    @Test()
    public void testExtractCanITeleportFromXToYQuestionLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractCanITeleportFromXToYQuestionLines(new String[0]);
    }

    @Test()
    public void testExtractCanITeleportFromXToYQuestionLines_ValidInput() {

        Set<String> expectedResult = new HashSet<String>() {{
            add("can I teleport from Springton to Atlantis");
            add("can I teleport from Oaktown to Atlantis");
        }};

        Set<String> result = teleporterInputUtil.extractCanITeleportFromXToYQuestionLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(result, expectedResult);
    }

    @Test
    public void testIsLoopPossiblefromXQuestionLine_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isLoopPossiblefromXQuestionLine(null);
    }

    @Test
    public void testIsLoopPossiblefromXQuestionLine_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isLoopPossiblefromXQuestionLine("");
    }

    @Test
    public void testIsLoopPossiblefromXQuestionLine_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.isLoopPossiblefromXQuestionLine(" ");
    }

    @Test
    public void testIsLoopPossiblefromXQuestionLine_ValidInputTrue() {

        boolean result = teleporterInputUtil.isLoopPossiblefromXQuestionLine(INPUT_LINE_IS_LOOP_POSSIBLE_FROM_X);

        assertTrue(result);
    }

    @Test
    public void testIsLoopPossiblefromXQuestionLine_ValidInputFalse() {

        boolean result = teleporterInputUtil.isLoopPossiblefromXQuestionLine(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS);

        assertFalse(result);
    }

    @Test()
    public void testExtractLoopPossiblefromXQuestionLines_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractLoopPossiblefromXQuestionLines(null);
    }

    @Test()
    public void testExtractLoopPossiblefromXQuestionLines_EmptyInput() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        teleporterInputUtil.extractLoopPossiblefromXQuestionLines(new String[0]);
    }

    @Test()
    public void testExtractLoopPossiblefromXQuestionLines_ValidInput() {

        Set<String> expectedResult = new HashSet<String>() {{
            add("loop possible from Oaktown");
            add("loop possible from Fortuna");
        }};

        Set<String> result = teleporterInputUtil.extractLoopPossiblefromXQuestionLines(TEST_INPUT_LINES_ARRAY);

        assertEquals(result, expectedResult);
    }

    @Test
    public void testParseRouteLineIntoCityNameArray_NullInput() {

        exceptionRule.expect(NullPointerException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.NULL_INPUT_LINE_MESSAGE);

        teleporterInputUtil.parseRouteLineIntoCityNameArray(null);
    }

    @Test
    public void testParseRouteLineIntoCityNameArray_BlankInputNoWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.parseRouteLineIntoCityNameArray("");
    }

    @Test
    public void testParseRouteLineIntoCityNameArray_BlankInputWhitespace() {

        exceptionRule.expect(IllegalArgumentException.class);
        exceptionRule.expectMessage(TeleporterInputUtil.BLANK_INPUT_LINE_MESSAGE);

        teleporterInputUtil.parseRouteLineIntoCityNameArray(" ");
    }

    @Test
    public void testParseRouteLineIntoCityNameArray_ValidInputTrue() {

        String[] expectedResult = new String[] {"Fortuna", "Hemingway"};
        String[] result = teleporterInputUtil.parseRouteLineIntoCityNameArray(INPUT_LINE_ROUTE);

        assertArrayEquals(result, expectedResult);
    }

}
