package com.voitov.java.api;


public class Main {
    public static void main(String[] args) {
        Data file = new Data();
        System.out.println(new Painter().racersString(file.getRacer()));
    }
}
