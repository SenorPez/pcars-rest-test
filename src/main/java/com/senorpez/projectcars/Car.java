package com.senorpez.projectcars;

public class Car {
    private final String name;
    private final String carClass;
    private final Integer topSpeed;
    private final Integer horsepower;
    private final Float acceleration;
    private final Float braking;
    private final Integer weight;
    private final Integer torque;
    private final Integer weightBalanceFront;
    private final Integer weightBalanceRear;
    private final Float wheelbase;

    public Car(String name, String carClass, Integer topSpeed, Integer horsepower, Float acceleration, Float braking, Integer weight, Integer torque, Integer weightBalanceFront, Float wheelbase) {
        this.name = name;
        this.carClass = carClass;
        this.topSpeed = topSpeed;
        this.horsepower = horsepower;
        this.acceleration = acceleration;
        this.braking = braking;
        this.weight = weight;
        this.torque = torque;
        this.weightBalanceFront = weightBalanceFront;
        this.weightBalanceRear = 100 - weightBalanceFront;
        this.wheelbase = wheelbase;
    }

    public String getName() {
        return name;
    }

    public String getCarClass() {
        return carClass;
    }

    public Integer getTopSpeed() {
        return topSpeed;
    }

    public Integer getHorsepower() {
        return horsepower;
    }

    public Float getAcceleration() {
        return acceleration;
    }

    public Float getBraking() {
        return braking;
    }

    public Integer getWeight() {
        return weight;
    }

    public Integer getTorque() {
        return torque;
    }

    public Integer getWeightBalanceFront() {
        return weightBalanceFront;
    }

    public Integer getWeightBalanceRear() {
        return weightBalanceRear;
    }

    public Float getWheelbase() {
        return wheelbase;
    }
}
