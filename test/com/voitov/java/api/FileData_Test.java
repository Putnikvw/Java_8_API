package com.voitov.java.api;

import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileData_Test {

    @Test
    public void checkTimeForRacer() {

        FileData file = new FileData();
        int racerSvf = 1;
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime startLapTime = LocalDateTime.parse("2018-05-24_12:02:58.917", timePattern);
        LocalDateTime finishLapTime = LocalDateTime.parse("2018-05-24_12:04:03.332", timePattern);
        Duration resultTime = Duration.between(startLapTime, finishLapTime);

        assertEquals(resultTime, file.getRacer().get(racerSvf).getTime());
    }

    @Test
    public void checkCarTitleForRacer() {

        FileData file = new FileData();
        int racerMes = 16;
        String fullCarTitle = "SAUBER FERRARI";
        assertEquals(fullCarTitle, file.getRacer().get(racerMes).getCar());
    }

    @Test
    public void checkFullNameForRacer() {

        FileData file = new FileData();
        int racerFam = 6;
        String fullRacerName = "Fernando Alonso";

        assertEquals(fullRacerName, file.getRacer().get(racerFam).getName());
    }



}