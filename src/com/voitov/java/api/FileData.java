package com.voitov.java.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

}

