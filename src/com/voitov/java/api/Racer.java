package com.voitov.java.api;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;


public class Racer {

    private String name;
    private String abbrev;
    private String car;
    private LocalDateTime time;

    public void setName(String name) {
        this.name = name;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }


    private void sortedRacerTime(FileData timeFile) {

        Map<String, String> tempMap = new HashMap<>();
        for (Map.Entry<String, String> entry : readLogFileTime(timeFile.getStartLogFile()).entrySet()) {
            String timeLap = getTimeDiff(entry.getValue(), readLogFileTime(timeFile.getEndLogFile()).get(entry.getKey()));
            tempMap.put(entry.getKey(), timeLap);
        }
//        time = tempMap.entrySet().stream()
//                .sorted(comparingByValue())
//                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }

    private Map<String, LocalDateTime> readLogFileTime(String filePath) {

        DateTimeFormatter currentDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        return Arrays.stream(filePath.split("\n"))
                .collect(toMap(x -> x.substring(0, 3), x -> LocalDateTime.parse(x.substring(3), currentDateTime)));

    }

    private String getTimeDiff(LocalDateTime start, LocalDateTime end) {
        return  Duration.between(start, end).toMillis();
    }

}
