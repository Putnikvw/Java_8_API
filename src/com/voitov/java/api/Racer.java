package com.voitov.java.api;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


public class Racer {

    private Map<String, String> name;
    private Map<String, String> car;
    private Map<String, String> time;

    public Racer(FileData files) {
        getFullRacerName(files.getAbbrevFile());
        getFullRacerCar(files.getAbbrevFile());
        sortedRacerTime(files);
    }

    public Map<String, String> getName() {
        return name;
    }

    public Map<String, String> getCar() {

        return car;
    }

    public Map<String, String> getTime() {

        return time;
    }

    private void getFullRacerName(String abbrevData) {

        name = Arrays.stream(abbrevData.split("\n"))
                .collect(toMap(x -> x.substring(0, 3), x -> x.substring(4, x.lastIndexOf("_"))));
    }

    private void getFullRacerCar(String abbrevData) {

        car = Arrays.stream(abbrevData.split("\n"))
                .collect(toMap(x -> x.substring(0, 3), x -> x.substring(x.lastIndexOf("_") + 1)));
    }

    private void sortedRacerTime(FileData timeFile) {

        Map<String, String> tempMap = new HashMap<>();
        for (Map.Entry<String, LocalDateTime> entry : readLogFileTime(timeFile.getStartLogFile()).entrySet()) {
            String timeLap = getTimeDiff(entry.getValue(), readLogFileTime(timeFile.getEndLogFile()).get(entry.getKey()));
            tempMap.put(entry.getKey(), timeLap);
        }
        time = tempMap.entrySet().stream()
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
