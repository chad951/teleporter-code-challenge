package com.cortek.solutions.teleportercodechallenge.util;

import com.cortek.solutions.teleportercodechallenge.query.CitiesFromXInNJumpsQuery;
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
}
