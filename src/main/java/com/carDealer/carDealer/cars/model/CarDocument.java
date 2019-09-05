package com.carDealer.carDealer.cars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "cars")
public class CarDocument {

    @Id
    private String id;

    private String make;
    private String model;
    private String engine;
    private int horsePower;
    private int basePrice;

    public CarDocument() {
    }

    public CarDocument(String make, String model, String engine, int horsePower, int basePrice) {
        this.make = make;
        this.model = model;
        this.engine = engine;
        this.horsePower = horsePower;
        this.basePrice = basePrice;
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

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    @Override
    public String toString() {
        return "CarDocument{" +
                "id='" + id + '\'' +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", engine='" + engine + '\'' +
                ", horsePower=" + horsePower +
                '}';
    }
}
