package com.voitov.java.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class WriteMap {

    private final String ABBREVIATION_FILE_PATH = "files/abbreviations.txt";
    private final String STARTLOG_FILE_PATH = "files/start.log";
    private final String ENDLOG_FILE_PATH = "files/end.log";

    private Map<String, String> lapTimeMap;
    private Map<String, String> abbrevMap;


    public Map<String, String> getLapTimeMap() {
        return lapTimeMap;
    }

    public Map<String, String> getAbbrevMap() {
        return abbrevMap;
    }

    public WriteMap() {

        try {
            countLapTime();
            setAbbrevMap();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private void countLapTime() throws IOException, ParseException {

        Map<String, Date> startLogMap = new HashMap<>();
        Map<String, Date> endLogMap = new HashMap<>();
        Map<String, String> tempMap = new HashMap<>();
        setLogMaps(STARTLOG_FILE_PATH, startLogMap);
        setLogMaps(ENDLOG_FILE_PATH, endLogMap);

        for (Map.Entry<String, Date> entry : startLogMap.entrySet()) {
            tempMap.put(entry.getKey(), getTimeDiff(entry.getValue(), endLogMap.get(entry.getKey())));
        }
        lapTimeMap = tempMap.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }


    private void setLogMaps(String filePath, Map<String, Date> exitMap) throws IOException, ParseException {

        Map<String, String> myMap = Files.lines(Paths.get(filePath)).filter(v -> !v.trim().isEmpty()).
                collect(toMap(x -> x.substring(0, 3), x -> x.substring(3)));

        SimpleDateFormat currentDateTime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        for (Map.Entry<String, String> entry : myMap.entrySet()) {
            exitMap.put(entry.getKey(), currentDateTime.parse(entry.getValue()));
        }
    }

    private void setAbbrevMap() throws IOException {

        abbrevMap = Files.lines(Paths.get(ABBREVIATION_FILE_PATH)).filter(v -> !v.trim().isEmpty()).
                collect(toMap(x -> x.substring(0, 3), x -> x.substring(4)));


    }

    private String getTimeDiff(Date start, Date end) {
        return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(end.getTime() - start.getTime()));
    }

}

