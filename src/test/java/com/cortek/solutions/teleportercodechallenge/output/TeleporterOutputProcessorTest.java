package com.cortek.solutions.teleportercodechallenge.output;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class TeleporterOutputProcessorTest {

    private static final String INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS = "cities from city1 in 1 jumps";

    private static final Set<String> cityNamesJumped = new HashSet<String>() {{
        add("city1");
        add("city2");
    }};

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @InjectMocks
    private TeleporterOutputProcessor teleporterOutputProcessor;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void testPrintCitiesFromXInNJumpsQueryLineResults_ForSystemOut() {

        String expecteResult = INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS + ": " + "city1 city2";

        teleporterOutputProcessor.printCitiesFromXInNJumpsQueryLineResults(INPUT_LINE_CITIES_FROM_X_TO_N_JUMPS, cityNamesJumped);

        assertEquals(expecteResult, outContent.toString().trim());
    }
}
