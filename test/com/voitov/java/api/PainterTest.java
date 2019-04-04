package com.voitov.java.api;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PainterTest {

    @Mock
    FileReader fileReader;

    @InjectMocks
    Data fileData;

    @Test
    public void checkPrintStringForRacer() {

        Painter print = new Painter();
        when(fileReader.readFile(anyString())).thenReturn("EOF_Esteban Ocon_FORCE INDIA MERCEDES").thenReturn("EOF2018-05-24_12:17:58.810")
                .thenReturn("EOF2018-05-24_12:12:11.838");
        assertEquals(print.racersString(fileData.getRacer()), "FORCE INDIA MERCEDES");

    }
}
