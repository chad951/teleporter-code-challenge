package com.cortek.solutions.teleportercodechallenge.output;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class TeleporterOutputProcessor {

    public void printCitiesFromXInNJumpsQueryLineResults(String citiesFromXInNJumpsQueryLine, Set<String> cityNamesJumped) {

        String output = citiesFromXInNJumpsQueryLine + ": ";

        List<String> cityNamesJumpedList = new ArrayList<>(cityNamesJumped);

        for (int i = 0; i < cityNamesJumped.size(); i++) {
            if (i + 1 == cityNamesJumped.size()) {
                output += cityNamesJumpedList.get(i);
            } else {
                output += cityNamesJumpedList.get(i) + " ";
            }
        }

        System.out.println(output);
    }
}
