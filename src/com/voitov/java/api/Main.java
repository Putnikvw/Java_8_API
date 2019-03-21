package com.voitov.java.api;


import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        Racer racerTime = new Racer();
        Racer racerAbbrev = new Racer();
        FileData file = new FileData();
        PrintRacer racers = new PrintRacer();
        racers.printRacersResult(racerTime.countLapTime(file.getStartLogFile(), file.getEndLogFile()),
                racerAbbrev.getAbbrevMap(file.getAbbrevFile()));
    }
}
