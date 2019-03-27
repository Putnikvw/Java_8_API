package com.voitov.java.api;


import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        FileData file = new FileData();
        ArrayList<Racer> list = new ArrayList<>();

        for (String loop: file.getDataForRacer().split("\n")) {
            list.add(file.getRacer(loop));
        }
        System.out.println(new Painter().racersString(list));
    }
}
