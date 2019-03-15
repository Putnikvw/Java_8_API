package com.voitov.java.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class WriteMap {

    private final String fileAbbreviationsPath = "files/abbreviations.txt";
    private final String fileStartPath = "files/start.log";
    private final String fileEndPath = "files/end.log";

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
            countTimeLap();
            writeAbbrevMap();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    private void countTimeLap() throws IOException, ParseException {

        Map<String, Date> startLogMap = new HashMap<>();
        Map<String, Date> endLogMap = new HashMap<>();
        Map<String, String> tempMap = new HashMap<>();
        writeLogMaps(fileStartPath, startLogMap);
        writeLogMaps(fileEndPath, endLogMap);

        for (Map.Entry<String, Date> entry : startLogMap.entrySet()) {
            tempMap.put(entry.getKey(), getTimeDiff(entry.getValue(), endLogMap.get(entry.getKey())));
        }
        lapTimeMap = tempMap.entrySet().stream().sorted(comparingByValue()).collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

    }


    private void writeLogMaps(String filePath, Map<String, Date> exitMap) throws IOException, ParseException {

        Map<String, String> myMap = Files.lines(Paths.get(filePath)).filter(v -> !v.trim().isEmpty()).
                collect(toMap(x -> x.substring(0, 3), x -> x.substring(3, x.length())));

        Iterator it = myMap.entrySet().iterator();
        SimpleDateFormat currentDateTime = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            exitMap.put((String) pair.getKey(), currentDateTime.parse((String) pair.getValue()));
            it.remove();
        }
    }

    private void writeAbbrevMap() throws IOException {

        abbrevMap = Files.lines(Paths.get(fileAbbreviationsPath)).filter(v -> !v.trim().isEmpty()).
                collect(toMap(x -> x.substring(0, 3), x -> x.substring(4, x.length())));


    }

    private String getTimeDiff(Date start, Date end) {
        return (new SimpleDateFormat("mm:ss:SSS")).format(new Date(end.getTime() - start.getTime()));
    }

}

