package com.voitov.java.api;


import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        RacerCache racerTime = new RacerCache();
        RacerCache racerAbbrev = new RacerCache();
        ReadFile file = new ReadFile();
        PrintRacer racers = new PrintRacer();
        racers.printRacersResult(racerTime.countLapTime(file.getStartLogFile(), file.getEndLogFile()),
                racerAbbrev.getAbbrevMap(file.getAbbrevFile()));
    }
}
