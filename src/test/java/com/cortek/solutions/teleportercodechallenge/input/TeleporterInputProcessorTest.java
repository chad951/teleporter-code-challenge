package com.cortek.solutions.teleportercodechallenge.input;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TeleporterInputProcessorTest {

    @InjectMocks
    private TeleporterInputProcessor teleporterInputProcessor;

    private String testData = "Fortuna - Hemingway\n" +
            "Fortuna - Atlantis\n" +
            "Hemingway - Chesterfield\n" +
            "Chesterfield - Springton\n" +
            "Los Amigos - Paristown\n" +
            "Paristown - Oaktown\n" +
            "Los Amigos - Oaktown\n" +
            "Summerton - Springton\n" +
            "Summerton - Hemingway\n" +
            "cities from Summerton in 1 jumps\n" +
            "cities from Summerton in 2 jumps\n" +
            "can I teleport from Springton to Atlantis\n" +
            "can I teleport from Oaktown to Atlantis\n" +
            "loop possible from Oaktown\n" +
            "loop possible from Fortuna";

    @Test
    public void testProcessInput_NonNullString() {

        teleporterInputProcessor.processInput(testData);
    }
}
