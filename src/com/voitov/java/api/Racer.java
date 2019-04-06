package com.voitov.java.api;

import java.time.Duration;

public class Racer implements Comparable<Racer> {

    private final String name;
    private final String car;
    private final Duration time ;

    public Racer(String name, String car, Duration time) {
        this.name = name;
        this.car = car;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public String getCar() {
        return car;
    }

    public Duration getTime() {
        return time;
    }


    @Override
    public int compareTo(Racer racer) {
        return this.time.compareTo(racer.getTime());
    }
}
