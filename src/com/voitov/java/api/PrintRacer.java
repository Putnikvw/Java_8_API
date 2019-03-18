package com.voitov.java.api;

import java.util.Map;

public class PrintRacer {

    public String printRacersResult() {

        WriteMap mapResult = new WriteMap();
        StringBuilder racers = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, String> entry: mapResult.getLapTimeMap().entrySet()) {
            String nameRacer = mapResult.getAbbrevMap().get(entry.getKey());
            racers.append(count).append(". ").append(nameRacer.replace("_", "\t\t| ")).append("\t| ").append(entry.getValue()).append("\n");
            if (count == 15) {
                racers.append("\n").append("-------------------------------------------------------").append("\n\n");
            }
            count++;
        }
        return racers.toString();
    }
}
