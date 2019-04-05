package com.voitov.java.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RacerBuilderTest {

    private final String ABBREV    = "SVF_Sebastian Vettel_FERRARI";
    private final String START_LOG = "SVF2018-05-24_12:02:58.917";
    private final String END_LOG   = "SVF2018-05-24_12:04:03.332";


    @Mock
    private FileReader fileReader;

    @InjectMocks
    private RacerBuilder fileData;

    @Test
    public void checkTimeForRacer() {
        LocalDateTime startLapTime = LocalDateTime.of(2018, 05, 24, 12, 02, 58, 917000000);
        LocalDateTime finishLapTime = LocalDateTime.of(2018, 05, 24, 12, 04, 03, 332000000);
        Duration resultTime = Duration.between(startLapTime, finishLapTime);
        when(fileReader.readFile(anyString())).thenReturn(ABBREV).thenReturn(START_LOG).thenReturn(END_LOG);
        assertEquals(fileData.getRacer().get(0).getTime(), resultTime);
    }

    @Test
    public void checkCarTitleForRacer() {
        when(fileReader.readFile(anyString())).thenReturn(ABBREV).thenReturn(START_LOG).thenReturn(END_LOG);
        assertEquals(fileData.getRacer().get(0).getCar(), "FERRARI");
    }

    @Test
    public void checkFullNameForRacer() {

        when(fileReader.readFile(anyString())).thenReturn(ABBREV).thenReturn(START_LOG).thenReturn(END_LOG);
        assertEquals(fileData.getRacer().get(0).getName(), "Sebastian Vettel");
    }


}