package com.voitov.java.api;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PainterTest {
    @Test
    public void checkPrintStringForRacer() {

        FileData file = new FileData();
        Painter print = new Painter();
        String firstRacer = print.racersString(file.getRacer()).substring(0, print.racersString(file.getRacer()).indexOf("\n"));
        String racerInTop = "1. Esteban Ocon\t\t| FORCE INDIA MERCEDES\t| 54:13.028";
        assertEquals(racerInTop, firstRacer);
    }
}
