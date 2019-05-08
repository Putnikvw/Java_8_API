package com.voitov.java.api;


public class Main {
    public static void main(String[] args) {
        RacerBuilder file = new RacerBuilder();
        System.out.println(new Painter().racersString(file.getRacer()));
    }
}
