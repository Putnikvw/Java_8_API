package com.voitov.java.api;

import java.util.Map;

public class Painter {

    public String printRacersResult(Racer racer) {

        StringBuilder race = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, String> entry : racer.getTime().entrySet()) {

            race.append(count)
                    .append(". ")
                    .append(racer.getName().get(entry.getKey()))
                    .append("\t\t| ")
                    .append(racer.getCar().get(entry.getKey()))
                    .append("\t| ")
                    .append(entry.getValue())
                    .append("\n");

            if (count == 15) {
                race.append("\n")
                        .append("-------------------------------------------------------")
                        .append("\n\n");
            }
            count++;
        }
        return race.toString();
    }
}
