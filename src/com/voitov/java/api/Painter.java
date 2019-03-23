package com.voitov.java.api;

import java.util.Map;

public class Painter {

    public String printRacersResult(Racer racer) {

        StringBuilder printList = new StringBuilder();
        int count = 1;
        for (Map.Entry<String, String> entry : racer.getTime().entrySet()) {

            printList.append(count)
                    .append(". ")
                    .append(racer.getName().get(entry.getKey()))
                    .append("\t\t| ")
                    .append(racer.getCar().get(entry.getKey()))
                    .append("\t| ")
                    .append(entry.getValue())
                    .append("\n");

            if (count == 15) {
                printList.append("\n")
                        .append("-------------------------------------------------------")
                        .append("\n\n");
            }
            count++;
        }
        return printList.toString();
    }
}
