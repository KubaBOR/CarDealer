package com.carDealer.carDealer.cars.dto;

public class Car {
    private String id;
    private String make;
    private String model;
    private String engine;
    private int horsePower;

    public Car(){}

    public Car(String make, String model, String engine, int horsePower) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.horsePower = horsePower;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public int getHorsePower() {
        return horsePower;
    }

    public void setHorsePower(int horsePower) {
        this.horsePower = horsePower;
    }
}
