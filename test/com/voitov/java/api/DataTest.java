package com.voitov.java.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DataTest {

    @Mock
    FileReader fileReader;

    @InjectMocks
    Data fileData;

    @Test
    public void checkTimeForRacer() {

        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
        LocalDateTime startLapTime = LocalDateTime.parse("2018-05-24_12:02:58.917", timePattern);
        LocalDateTime finishLapTime = LocalDateTime.parse("2018-05-24_12:04:03.332", timePattern);
        Duration resultTime = Duration.between(startLapTime, finishLapTime);
        when(fileReader.readFile(anyString())).thenReturn("SVF_Sebastian Vettel_FERRARI").thenReturn("SVF2018-05-24_12:02:58.917")
                .thenReturn("SVF2018-05-24_12:04:03.332");
        assertEquals(fileData.getRacer().get(0).getTime(), resultTime);
    }

    @Test
    public void checkCarTitleForRacer() {

        when(fileReader.readFile(anyString())).thenReturn("EOF_Esteban Ocon_FORCE INDIA MERCEDES").thenReturn("EOF2018-05-24_12:17:58.810")
                .thenReturn("EOF2018-05-24_12:12:11.838");
        assertEquals(fileData.getRacer().get(0).getCar(), "FORCE INDIA MERCEDES");
    }

    @Test
    public void checkFullNameForRacer() {

        when(fileReader.readFile(anyString())).thenReturn("EOF_Esteban Ocon_FORCE INDIA MERCEDES").thenReturn("EOF2018-05-24_12:17:58.810")
                .thenReturn("EOF2018-05-24_12:12:11.838");
        assertEquals(fileData.getRacer().get(0).getName(), "Esteban Ocon");
    }


}