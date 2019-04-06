package com.voitov.java.api;

import org.junit.Test;
import java.time.Duration;
import java.util.Collections;
import java.util.List;
import static org.junit.Assert.assertEquals;


public class PainterTest {

    @Test
    public void checkPrintStringForRacer() {
        Painter print = new Painter();
        List <Racer> racers = Collections.singletonList(new Racer("Esteban Ocon", "FORCE INDIA MERCEDES", Duration.ofMillis(70028)));
        assertEquals(print.racersString(racers), "1. Esteban Ocon\t\t| FORCE INDIA MERCEDES\t| 01:10.028\n");

    }
}
