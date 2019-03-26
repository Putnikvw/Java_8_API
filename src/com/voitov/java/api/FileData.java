package com.voitov.java.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

    private void some() throws IOException {
        String abbrev = Files.lines(Paths.get(ABBREVIATION_FILE_PATH)).filter(v -> !v.trim().isEmpty()).forEach();                                                                                                          ) {

        }

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

    private Racer getRacer(String fileDataString) {

        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        Racer racer = new Racer();
        racer.setAbbrev(fileDataString.substring(0, fileDataString.indexOf("_")));
        racer.setName(fileDataString.substring(fileDataString.indexOf("_") + 1, fileDataString.lastIndexOf("_")));
        racer.setCar(fileDataString.substring(fileDataString.lastIndexOf("_")));
        racer.setTime(LocalDateTime.parse(fileDataString, currentDateTime));
        return racer;

    }

    private String countLapTime(String startTime, String endTime) {
        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime start = LocalDateTime.parse(startTime, currentDateTime);
        LocalDateTime end = LocalDateTime.parse(endTime, currentDateTime);
        return (new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS")).format(Duration.between(start, end));
    }

}

