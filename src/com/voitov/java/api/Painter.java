package com.voitov.java.api;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;

public class Painter {

    public String racersString(List<Racer> racers) {

        StringBuilder race = new StringBuilder();
        racers.sort(Comparator.comparing(Racer::getTime));
        for (int i = 1; i < racers.size(); i++) {
            race.append(i)
                    .append(". ")
                    .append(racers.get(i).getName())
                    .append("\t\t| ")
                    .append(racers.get(i).getCar())
                    .append("\t| ")
                    .append((new SimpleDateFormat("mm:ss.SSS")).format(racers.get(i).getTime().toMillis()))
                    .append("\n");
            if (i == 15) {
                race.append("\n")
                        .append("-------------------------------------------------------")
                        .append("\n\n");
            }
        }
        return race.toString();
    }

}
