package com.voitov.java.api;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JUnit_WriteMap {

    @Test
    public void checkCountMapTimeForRacer() throws ParseException {

        WriteMap monacoRacers = new WriteMap();
        String racerKey = "SVF";
        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss.SSS");
        Date startLapTime = dateTimeFormat.parse("2018-05-24_12:02:58.917");
        Date finishLapTime = dateTimeFormat.parse("2018-05-24_12:04:03.332");
        String resultTime = (new SimpleDateFormat("mm:ss:SSS")).format(new Date(finishLapTime.getTime() - startLapTime.getTime()));

        assertEquals(resultTime, monacoRacers.getLapTimeMap().get(racerKey));
    }

    @Test
    public void checkFinalPrintStringForRacer() {
        WriteMap finalRacersList = new WriteMap();
        String racerKey = "BHS";
        String equalsList = "12. Brendon Hartley\t\t| SCUDERIA TORO ROSSO HONDA\t| 01:13:179\n";
        StringBuilder racerExamplePrint = new StringBuilder();

        racerExamplePrint.append("12. ").append(finalRacersList.getAbbrevMap().get(racerKey).replace("_", "\t\t| ")).
                    append("\t| ").append(finalRacersList.getLapTimeMap().get(racerKey)).append("\n");

        assertEquals(equalsList, racerExamplePrint.toString());

    }
}