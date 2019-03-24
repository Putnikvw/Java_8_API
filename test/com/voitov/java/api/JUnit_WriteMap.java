package com.voitov.java.api;

import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JUnit_WriteMap {

    @Test
    public void checkTimeForRacer() {

        Racer racer = new Racer(new FileData());
        String racerKey = "SVF";
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime startLapTime = LocalDateTime.parse("2018-05-24_12:02:58.917", timePattern);
        LocalDateTime finishLapTime = LocalDateTime.parse("2018-05-24_12:04:03.332", timePattern);
        String resultTime = (new SimpleDateFormat("mm:ss:SSS")).format(Duration.between(startLapTime, finishLapTime).toMillis());

        assertEquals(resultTime, racer.getTime().get(racerKey));
    }

    @Test
    public void checkCarTitleForRacer() {

        Racer racer = new Racer(new FileData());
        String racerKey = "MES";
        String fullCarTitle = "SAUBER FERRARI";

        assertEquals(fullCarTitle, racer.getCar().get(racerKey).trim());
    }

    @Test
    public void checkFullNameForRacer() {

        Racer racer = new Racer(new FileData());
        String racerKey = "FAM";
        String fullRacerName = "Fernando Alonso";

        assertEquals(fullRacerName, racer.getName().get(racerKey).trim());
    }

}