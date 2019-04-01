package com.voitov.java.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DataTest {

    @InjectMocks
    private Data fileData;

    @Test
    public void checkTimeForRacer() {

        int racerSvf = 1;
        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime startLapTime = LocalDateTime.parse("2018-05-24_12:02:58.917", timePattern);
        LocalDateTime finishLapTime = LocalDateTime.parse("2018-05-24_12:04:03.332", timePattern);
        Duration resultTime = Duration.between(startLapTime, finishLapTime);
        assertEquals(resultTime, fileData.getRacer().get(racerSvf).getTime());
    }

    @Test
    public void checkCarTitleForRacer() {

        int racerMes = 16;
        String fullCarTitle = "SAUBER FERRARI";
        assertEquals(fullCarTitle, fileData.getRacer().get(racerMes).getCar());
    }

    @Test
    public void checkFullNameForRacer() {

        int racerFam = 6;
        String fullRacerName = "Fernando Alonso";
        assertEquals(fullRacerName, fileData.getRacer().get(racerFam).getName());
    }



}