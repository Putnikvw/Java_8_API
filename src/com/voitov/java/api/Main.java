package com.voitov.java.api;


public class Main {
    public static void main(String[] args) {
        Racer racer;
        FileData file = new FileData();

        for (String loop: file.createRacer().split("\n")) {
            racer = file.getRacer(loop);
            System.out.println(racer.getAbbrev() + "\n" + racer.getName() + "\n" + racer.getCar() + "\n"
                    +  racer.getTime());
        }

    }
}
