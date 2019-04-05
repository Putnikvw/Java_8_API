package com.voitov.java.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PainterTest {

    private final String ABBREV    = "EOF_Esteban Ocon_FORCE INDIA MERCEDES";
    private final String START_LOG = "EOF2018-05-24_12:17:58.810";
    private final String END_LOG   = "EOF2018-05-24_12:12:11.838";

    @Mock
    FileReader fileReader;

    @InjectMocks
    RacerBuilder fileData;

    @InjectMocks
    Painter print;

    @Test
    public void checkPrintStringForRacer() {

        String finalPrint = "1. Esteban Ocon\t\t| FORCE INDIA MERCEDES\t| 54:13.028\n";
        when(fileReader.readFile(anyString())).thenReturn(ABBREV).thenReturn(START_LOG).thenReturn(END_LOG);
        assertEquals(print.racersString(fileData.getRacer()), finalPrint);

    }
}
