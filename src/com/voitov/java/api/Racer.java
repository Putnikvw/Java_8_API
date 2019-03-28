package com.voitov.java.api;

import java.time.Duration;

public class Racer implements Comparable<Racer> {

    private String name;
    private String abbrev;
    private String car;
    private Duration time;

    public String getName() {
        return name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getCar() {
        return car;
    }

    public Duration getTime() {
        return time;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public void setTime(Duration time) {
        this.time = time;
    }

    @Override
    public int compareTo(Racer o) {
        return 0;
    }
}
