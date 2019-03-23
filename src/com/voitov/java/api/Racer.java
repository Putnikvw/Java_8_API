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

    public Map<String, String> getName() {
        return getFullRacerName(new FileData().getAbbrevFile());
    }

    public Map<String, String> getCar() {
        return getFullRacerCar(new FileData().getAbbrevFile());
    }

    public Map<String, String> getTime() {
        return sortedRacerTime();
    }

    private Map<String, String> getFullRacerName(String abbrevData) {

        return Arrays.stream(abbrevData.split("\n"))
                .collect(Collectors.toMap(x -> x.substring(0, 3), x -> x.substring(4, x.lastIndexOf("_"))));
    }

    private Map<String, String> getFullRacerCar(String abbrevData) {

        return Arrays.stream(abbrevData.split("\n"))
                .collect(Collectors.toMap(x -> x.substring(0, 3), x -> x.substring(x.lastIndexOf("_") + 1)));
    }

    private Map<String, String> sortedRacerTime() {

        Map<String, String> tempMap = new HashMap<>();
        FileData fileName = new FileData();
        for (Map.Entry<String, LocalDateTime> entry : readLogFileTime(fileName.getStartLogFile()).entrySet()) {
            String timeLap = getTimeDiff(entry.getValue(), readLogFileTime(fileName.getEndLogFile()).get(entry.getKey()));
            tempMap.put(entry.getKey(), timeLap);
        }
        return tempMap.entrySet().stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    private Map<String, LocalDateTime> readLogFileTime(String filePath) {

        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return Arrays.stream(filePath.split("\n"))
                .collect(toMap(x -> x.substring(0, 3), x -> LocalDateTime.parse(x.substring(3), currentDateTime)));

    }

    private String getTimeDiff(LocalDateTime start, LocalDateTime end) {
        return (new SimpleDateFormat("mm:ss:SSS")).format(Duration.between(start, end).toMillis());
    }

}
