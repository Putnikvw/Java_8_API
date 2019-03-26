package com.voitov.java.api;

import java.time.LocalDateTime;


public class Racer {

    private String name;
    private String abbrev;
    private String car;
    private LocalDateTime time;

    public String getName() {
        return name;
    }

    public String getAbbrev() {
        return abbrev;
    }

    public String getCar() {
        return car;
    }

    public LocalDateTime getTime() {
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

    public void setTime(LocalDateTime time) {
        this.time = time;
    }




}
