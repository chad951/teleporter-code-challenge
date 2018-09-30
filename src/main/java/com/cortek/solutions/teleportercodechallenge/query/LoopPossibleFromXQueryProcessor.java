package com.cortek.solutions.teleportercodechallenge.query;

import com.cortek.solutions.teleportercodechallenge.input.TeleporterInputUtil;
import com.cortek.solutions.teleportercodechallenge.output.TeleporterOutputProcessor;
import com.cortek.solutions.teleportercodechallenge.route.RoutePath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

@Component
public class LoopPossibleFromXQueryProcessor implements QueryProcessor {

    private TeleporterInputUtil teleporterInputUtil;

    private QueryUtil queryUtil;

    private TeleporterOutputProcessor teleporterOutputProcessor;

    @Autowired
    public LoopPossibleFromXQueryProcessor(TeleporterInputUtil teleporterInputUtil, QueryUtil queryUtil, TeleporterOutputProcessor teleporterOutputProcessor) {
        this.teleporterInputUtil = teleporterInputUtil;
        this.queryUtil = queryUtil;
        this.teleporterOutputProcessor = teleporterOutputProcessor;
    }

    @Override
    public void processQueryLines(String[] inputLines, Map<String, Set<RoutePath>> routePathsByCityName) {

        Set<String> loopPossibleFromXQueryLines = teleporterInputUtil.extractLoopPossiblefromXQueryLines(inputLines);

        loopPossibleFromXQueryLines.forEach(loopPossibleFromXQueryLine -> {

        });
    }

    public boolean isLoopPossible(String canITeleportFromXToYQueryLine, Map<String, Set<RoutePath>> routePathsByCityName) {

       // CanITeleportFromXToYQuery canITeleportFromXToYQuery = queryUtil.parseL(canITeleportFromXToYQueryLine);

        return true;
    }
}
