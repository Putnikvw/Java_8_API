package com.voitov.java.api;

public class Racer {

    private String name;
    private String abbrev;
    private String car;
    private long time;

    public String getName() {
        return name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getCar() {
        return car;
    }

    public long getTime() {
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

    public void setTime(long time) {
        this.time = time;
    }

}
