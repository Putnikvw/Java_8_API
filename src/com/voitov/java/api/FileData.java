package com.voitov.java.api;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileData {

    private static final String ABBREVIATION_FILE_PATH = "files/abbreviations.txt";
    private static final String STARTLOG_FILE_PATH = "files/start.log";
    private static final String ENDLOG_FILE_PATH = "files/end.log";


    public List<Racer> getRacer() {

        List<Racer> list = new ArrayList<>();

        for (String loop : getDataForRacer().split("\n")) {

            String name =loop.substring(loop.indexOf("_") + 1, loop.lastIndexOf("_"));
            String car = loop.substring(loop.lastIndexOf("_") + 1, loop.indexOf("&"));
            Duration time = Duration.parse(loop.substring(loop.indexOf("&") + 1));
            list.add(new Racer(name, car, time));
        }
        return list;
    }

    private String getDataForRacer() {

        StringBuilder finalString = new StringBuilder();
        for (String loop : readFile(ABBREVIATION_FILE_PATH).split("\n")) {
            String startTime = getLogFilesData(loop, STARTLOG_FILE_PATH);
            String endTime = getLogFilesData(loop, ENDLOG_FILE_PATH);

            finalString.append(loop)
                    .append("&")
                    .append(countLapTime(startTime, endTime))
                    .append("\n");
        }
        return finalString.toString();
    }

    private String getLogFilesData(String abbrevString, String logFile) {
        return Arrays.stream(readFile(logFile)
                .split("\n"))
                .filter(x -> x.contains(abbrevString.substring(0, 3)))
                .findFirst()
                .map(x -> x.substring(3))
                .map(x -> x.replace("_", "!"))
                .get();
    }

    private String readFile(String filePath) {
        StringBuilder log = new StringBuilder();
        try {
            Files.lines(Paths.get(filePath))
                    .filter(v -> !v.trim().isEmpty())
                    .forEach(x -> log.append(x).append("\n"));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
        return log.toString();
    }

    private Duration countLapTime(String startTime, String endTime) {
        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd!HH:mm:ss.SSS");
        LocalDateTime start = LocalDateTime.parse(startTime, currentDateTime);
        LocalDateTime end = LocalDateTime.parse(endTime, currentDateTime);
        return Duration.between(start, end);
    }

}

