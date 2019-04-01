package com.voitov.java.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class PainterTest {

    @InjectMocks
    private Data fileData;
    @InjectMocks
    private Painter print;

    @Test
    public void checkPrintStringForRacer() {

        String firstRacer = print.racersString(fileData.getRacer()).substring(0, print.racersString(fileData.getRacer()).indexOf("\n"));
        String racerInTop = "1. Esteban Ocon\t\t| FORCE INDIA MERCEDES\t| 54:13.028";
        assertEquals(racerInTop, firstRacer);
    }
}
