package com.voitov.java.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class RacerCache {

    public Map<String, String> getAbbrevMap(String abbrevData) {

        return Arrays.asList(abbrevData.split("\n"))
                .stream()
                .collect(Collectors.toMap(x -> x.substring(0, 3), x -> x.substring(4)));
    }

    public Map<String, String> countLapTime(String startLogData, String endLogData) throws ParseException {

        Map<String, Date> startLogMap = new HashMap<>();
        Map<String, Date> endLogMap = new HashMap<>();
        Map<String, String> tempMap = new HashMap<>();

        setLogMaps(startLogData, startLogMap);
        setLogMaps(endLogData, endLogMap);


        for (Map.Entry<String, Date> entry : startLogMap.entrySet()) {
            tempMap.put(entry.getKey(), getTimeDiff(entry.getValue(), endLogMap.get(entry.getKey())));
        }
        return tempMap.entrySet().stream()
                .sorted(comparingByValue())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }


    private void setLogMaps(String filePath, Map<String, Date> exitMap) throws ParseException {

        Map<String, String> myMap = Arrays.asList(filePath.split("\n"))
                .stream()
                .collect(toMap(x -> x.substring(0, 3), x -> x.substring(3)));

        SimpleDateFormat currentDateTime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        for (Map.Entry<String, String> entry : myMap.entrySet()) {
            exitMap.put(entry.getKey(), currentDateTime.parse(entry.getValue()));
        }
    }

    private String getTimeDiff(Date start, Date end) {
        return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(end.getTime() - start.getTime()));
    }

}
