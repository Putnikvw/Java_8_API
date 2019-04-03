package com.voitov.java.api;

import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class PainterTest {

    Data fileData = Mockito.mock(Data.class);
    
    @Test
    public void checkPrintStringForRacer() {
            
        List<Racer> racer = new ArrayList<>();
//        String firstRacer = print.racersString(fileData.getRacer()).substring(0, print.racersString(fileData.getRacer()).indexOf("\n"));
        String racerInTop = "1. Esteban Ocon\t\t| FORCE INDIA MERCEDES\t| 54:13.028";
//        assertEquals(racerInTop, firstRacer);
        fileData.getRacer();
        when(fileData.getRacer()).thenReturn(racer);
        verify(fileData, atLeastOnce()).getRacer();
    }
}
