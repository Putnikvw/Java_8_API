package com.voitov.java.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class FileData {

    private static final String ABBREVIATION_FILE_PATH = "files/abbreviations.txt";
    private static final String STARTLOG_FILE_PATH = "files/start.log";
    private static final String ENDLOG_FILE_PATH = "files/end.log";


    public String getStartLogFile() {
        return readFile(STARTLOG_FILE_PATH);
    }

    public String getEndLogFile() {
        return readFile(ENDLOG_FILE_PATH);
    }

    public String getAbbrevFile() {
        return readFile(ABBREVIATION_FILE_PATH);
    }

    public String createRacer() {
        StringBuilder finalString = new StringBuilder();
        for (String loop : getAbbrevFile().split("\n")) {
            String startTime = Arrays.stream(getStartLogFile()
                    .split("\n"))
                    .filter(x -> x.contains(loop.substring(0, 3)))
                    .findFirst()
                    .map(x -> x.substring(3))
                    .map(x-> x.replace("_", "!"))
                    .get();
            String endTime = Arrays.stream(getEndLogFile()
                    .split("\n"))
                    .filter(x -> x.contains(loop.substring(0, 3)))
                    .findFirst()
                    .map(x -> x.substring(3))
                    .map(x-> x.replace("_", "!"))
                    .get();

            finalString.append(loop)
                       .append("&")
                       .append(countLapTime(startTime, endTime))
                       .append("\n");
        }
        return finalString.toString();

    }

    public Racer getRacer(String fileDataString) {

        Racer racer = new Racer();
        racer.setAbbrev(fileDataString.substring(0, fileDataString.indexOf("_")));
        racer.setName(fileDataString.substring(fileDataString.indexOf("_") + 1, fileDataString.lastIndexOf("_")));
        racer.setCar(fileDataString.substring(fileDataString.lastIndexOf("_") + 1,  fileDataString.indexOf("&")));
        racer.setTime(fileDataString.substring(fileDataString.indexOf("&") + 1));
        return racer;

    }

    private String readFile(String filePath) {
        StringBuilder log = new StringBuilder();
        try {
            Files.lines(Paths.get(filePath))
                    .filter(v -> !v.trim().isEmpty())
                    .forEach(x -> log.append(x).append("\n"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return log.toString();
    }

    private String countLapTime(String startTime, String endTime) {
        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd!HH:mm:ss.SSS");
        LocalDateTime start = LocalDateTime.parse(startTime, currentDateTime);
        LocalDateTime end = LocalDateTime.parse(endTime, currentDateTime);
        return (new SimpleDateFormat("mm:ss:SSS")).format(Duration.between(start, end).toMillis());
    }

    //        time = tempMap.entrySet().stream()
//                .sorted(comparingByValue())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

}

