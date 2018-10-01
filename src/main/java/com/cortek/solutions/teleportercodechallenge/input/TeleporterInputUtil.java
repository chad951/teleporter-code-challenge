package com.cortek.solutions.teleportercodechallenge.input;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeleporterInputUtil {

    public static final String NULL_INPUT_LINE_MESSAGE = "inputLine cannot be null";

    public static final String BLANK_INPUT_LINE_MESSAGE = "inputLine cannot be blank";

    public static final String NULL_INPUT_LINE_ARRAY_MESSAGE = "inputLine array cannot be null";

    public static final String EMPTY_INPUT_LINE_ARRAY_MESSAGE = "inputLine array cannot be empty";

    public boolean isRouteLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.replaceAll(" ", "").matches("[A-Za-z][A-Za-z,-]*-[A-Za-z,-]*[A-Za-z]");
    }

    public boolean isCitiesFromXInNJumpsQueryLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.startsWith("cities from");
    }

    public boolean isCanITeleportFromXToYQueryLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.startsWith("can I teleport from");
    }

    public boolean isLoopPossiblefromXQueryLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.startsWith("loop possible from");
    }

    public List<String> extractRouteLines(String[] inputLines) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        List<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isRouteLine(inputLine)).collect(Collectors.toList());

        return routeSet;
    }

    public List<String> extractCitiesFromXInNJumpsQueryLines(String[] inputLines) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        List<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isCitiesFromXInNJumpsQueryLine(inputLine)).collect(Collectors.toList());

        return routeSet;
    }

    public List<String> extractCanITeleportFromXToYQueryLines(String[] inputLines) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        List<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isCanITeleportFromXToYQueryLine(inputLine)).collect(Collectors.toList());

        return routeSet;
    }

    public List<String> extractLoopPossiblefromXQueryLines(String[] inputLines) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        List<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isLoopPossiblefromXQueryLine(inputLine)).collect(Collectors.toList());

        return routeSet;
    }

}
