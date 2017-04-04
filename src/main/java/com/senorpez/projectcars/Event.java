package com.senorpez.projectcars;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class Event {
    private final String name;
    private final List<Round> rounds;
    private final List<Car> cars;

    @JsonIgnore
    private final String carFilter;

    public Event(String name, List<Round> rounds, List<Car> cars, String carFilter) {
        this.name = name;
        this.rounds = rounds;
        this.cars = cars;
        this.carFilter = carFilter;
    }

    public List<Car> getCars() {
        return cars;
    }

    public String getName() {
        return name;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public String getCarFilter() {
        return carFilter;
    }
}
