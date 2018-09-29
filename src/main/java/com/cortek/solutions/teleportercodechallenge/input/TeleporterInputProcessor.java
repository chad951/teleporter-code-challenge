package com.cortek.solutions.teleportercodechallenge.input;

import com.cortek.solutions.teleportercodechallenge.util.TeleporterInputUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class TeleporterInputProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    @Autowired
    public TeleporterInputProcessor(TeleporterInputUtil teleporterInputUtil) {
        this.teleporterInputUtil = teleporterInputUtil;
    }

    public void processInput(String input) {

        String inputLines[] = input.split("\\r?\\n");

        Set<String> routeLines = teleporterInputUtil.extractRouteLines(inputLines);
    }
}
