package com.voitov.java.api;

import java.util.Map;

public class PrintRacer {

    public void printRacersResult(Map<String, String> mapRacersTime, Map<String, String> mapAbbrev) {

        StringBuilder racers = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, String> entry : mapRacersTime.entrySet()) {
            String nameRacer = mapAbbrev
                    .get(entry.getKey());

            racers.append(count)
                    .append(". ")
                    .append(nameRacer.replace("_", "\t\t| "))
                    .append("\t| ")
                    .append(entry.getValue())
                    .append("\n");

            if (count == 15) {
                racers.append("\n")
                        .append("-------------------------------------------------------")
                        .append("\n\n");
            }
            count++;
        }
        System.out.println(racers.toString());
    }
}
