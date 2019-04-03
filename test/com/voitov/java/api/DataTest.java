package com.voitov.java.api;

import org.junit.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DataTest {


    @Test
    public void checkDataFromFileReader() {

        FileReader fileData = Mockito.mock(FileReader.class);
        Data data = Mockito.mock(Data.class);
        Duration time = Duration.ofMillis(20);
        List<Racer> racers = new ArrayList<>();
        racers.add(new Racer("Alan", "Ferrari", time));
        String filePath = "Test";
        when(fileData.readFile(filePath)).thenReturn("EOF_Esteban Ocon_FORCE INDIA MERCEDES").thenReturn("EOF2018-05-24_12:17:58.810")
                .thenReturn("EOF2018-05-24_12:12:11.838");
        when(data.getRacer()).thenReturn(racers);
        assertEquals(data.getRacer().get(0).getName(), racers.get(0).getName());
    }


//    @Test
//    public void checkTimeForRacer() {
//
//        int racerSvf = 1;
//        DateTimeFormatter timePattern = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
//        LocalDateTime startLapTime = LocalDateTime.parse("2018-05-24_12:02:58.917", timePattern);
//        LocalDateTime finishLapTime = LocalDateTime.parse("2018-05-24_12:04:03.332", timePattern);
//        Duration resultTime = Duration.between(startLapTime, finishLapTime);
//        assertEquals(resultTime, fileData.getRacer().get(racerSvf).getTime());
//    }
//
//    @Test
//    public void checkCarTitleForRacer() {
//
//        int racerMes = 16;
//        String fullCarTitle = "SAUBER FERRARI";
//        assertEquals(fullCarTitle, fileData.getRacer().get(racerMes).getCar());
//    }
//
//    @Test
//    public void checkFullNameForRacer() {
//
//        int racerFam = 6;
//        String fullRacerName = "Fernando Alonso";
//        assertEquals(fullRacerName, fileData.getRacer().get(racerFam).getName());
//    }


}