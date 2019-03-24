package com.voitov.java.api;


public class Main {
    public static void main(String[] args) {

        Painter race = new Painter();
        FileData file = new FileData();
        System.out.println(race.printRacersResult(new Racer(file)));
    }
}
