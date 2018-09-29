package com.cortek.solutions.teleportercodechallenge.util;

import org.apache.commons.lang3.Validate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
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

    public String[] parseRouteLineIntoCityNameArray(String routeInputLine) {

        Validate.notNull(routeInputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(routeInputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return routeInputLine.replaceAll(" ", "").split("-");
    }

    public boolean isCitiesFromXInNJumpsQuestionLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.startsWith("cities from");
    }

    public boolean isCanITeleportFromXToYQuestionLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.startsWith("can I teleport from");
    }

    public boolean isLoopPossiblefromXQuestionLine(String inputLine) {

        Validate.notNull(inputLine, NULL_INPUT_LINE_MESSAGE);
        Validate.notBlank(inputLine.trim(), BLANK_INPUT_LINE_MESSAGE);

        return inputLine.startsWith("loop possible from");
    }

    public Set<String> extractRouteLines(String inputLines[]) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        Set<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isRouteLine(inputLine)).collect(Collectors.toSet());

        return routeSet;
    }

    public Set<String> extractCitiesFromXInNJumpsQuestionLines(String inputLines[]) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        Set<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isCitiesFromXInNJumpsQuestionLine(inputLine)).collect(Collectors.toSet());

        return routeSet;
    }

    public Set<String> extractCanITeleportFromXToYQuestionLines(String inputLines[]) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        Set<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isCanITeleportFromXToYQuestionLine(inputLine)).collect(Collectors.toSet());

        return routeSet;
    }

    public Set<String> extractLoopPossiblefromXQuestionLines(String inputLines[]) {

        Validate.notNull(inputLines, NULL_INPUT_LINE_ARRAY_MESSAGE);
        Validate.notEmpty(inputLines, EMPTY_INPUT_LINE_ARRAY_MESSAGE);

        Set<String> routeSet = Arrays.stream(inputLines).filter(inputLine -> isLoopPossiblefromXQuestionLine(inputLine)).collect(Collectors.toSet());

        return routeSet;
    }
}
