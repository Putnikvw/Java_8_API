package com.voitov.java.api;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


public class Racer {

    private Map<String, String> name;
    private Map<String, String> car;
    private Map<String, String> time;

    public Map<String, String> getAbbrevMap(String abbrevData) {

        return Arrays.asList(abbrevData.split("\n"))
                .stream()
                .collect(Collectors.toMap(x -> x.substring(0, 3), x -> x.substring(4)));
    }

    public Map<String, String> countLapTime(String startLogData, String endLogData) {

        Map<String, LocalDateTime> startLogMap = new HashMap<>();
        Map<String, LocalDateTime> endLogMap = new HashMap<>();
        Map<String, String> tempMap = new HashMap<>();

        setTime(startLogData, startLogMap);
        setTime(endLogData, endLogMap);


        for (Map.Entry<String, LocalDateTime> entry : startLogMap.entrySet()) {
            tempMap.put(entry.getKey(), getTimeDiff(entry.getValue(), endLogMap.get(entry.getKey())));
        }
        return time = tempMap.entrySet().stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }


    private void setTime(String filePath, Map<String, LocalDateTime> exitMap) {

        Map<String, String> myMap = Arrays.asList(filePath.split("\n"))
                .stream()
                .collect(toMap(x -> x.substring(0, 3), x -> x.substring(3)));

        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        for (Map.Entry<String, String> entry : myMap.entrySet()) {
            exitMap.put(entry.getKey(), LocalDateTime.parse(entry.getValue(), currentDateTime));
        }
    }

    private String getTimeDiff(LocalDateTime start, LocalDateTime end) {
        return (new SimpleDateFormat("mm:ss:SSS")).format(Duration.between(start, end).toMillis());
    }

}
