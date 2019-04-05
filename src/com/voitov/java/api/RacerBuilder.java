package com.voitov.java.api;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RacerBuilder {

    private static final String ABBREVIATION_FILE_PATH = "files/abbreviations.txt";
    private static final String STARTLOG_FILE_PATH = "files/start.log";
    private static final String ENDLOG_FILE_PATH = "files/end.log";
    private FileReader fileData = new FileReader();


    public List<Racer> getRacer() {

        List<Racer> list = new ArrayList<>();

        for (String dataRacers : getDataForRacer().split("\n")) {

            String name = dataRacers.substring(dataRacers.indexOf("_") + 1, dataRacers.lastIndexOf("_"));
            String car = dataRacers.substring(dataRacers.lastIndexOf("_") + 1, dataRacers.indexOf("&"));
            Duration time = Duration.parse(dataRacers.substring(dataRacers.indexOf("&") + 1));
            list.add(new Racer(name, car, time));
        }
        return list;
    }

    private String getDataForRacer() {
        StringBuilder finalString = new StringBuilder();
        for (String abbrevData : fileData.readFile(ABBREVIATION_FILE_PATH).split("\n")) {
            String startTime = getLogFilesData(abbrevData, STARTLOG_FILE_PATH);
            String endTime = getLogFilesData(abbrevData, ENDLOG_FILE_PATH);

            finalString.append(abbrevData)
                    .append("&")
                    .append(countLapTime(startTime, endTime))
                    .append("\n");
        }
        return finalString.toString();
    }

    private String getLogFilesData(String abbrevString, String logFile) {
        return Arrays.stream(fileData.readFile(logFile).split("\n"))
                .filter(x -> x.contains(abbrevString.substring(0, 3)))
                .findFirst()
                .map(x -> x.substring(3))
                .map(x -> x.replace("_", "!"))
                .get();
    }

    private Duration countLapTime(String startTime, String endTime) {
        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd!HH:mm:ss.SSS");
        LocalDateTime start = LocalDateTime.parse(startTime, currentDateTime);
        LocalDateTime end = LocalDateTime.parse(endTime, currentDateTime);
        return Duration.between(start, end);
    }

}

