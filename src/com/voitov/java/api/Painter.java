package com.voitov.java.api;

public class Painter {

    public String printRacersResult(Racer racer, int count) {

        StringBuilder race = new StringBuilder();


            race.append(count)
                    .append(". ")

                    .append("\t\t| ")

                    .append("\t| ")

                    .append("\n");

            if (count == 15) {
                race.append("\n")
                        .append("-------------------------------------------------------")
                        .append("\n\n");
            }

        return race.toString();
    }
}
