package com.championships.Championships.auction.dto;

import com.championships.Championships.cars.dto.Car;
import com.championships.Championships.configuration.dto.Configuration;

import java.util.ArrayList;
import java.util.List;

public class Auction {

    private String id;
    private String title;
    private List<Car> carList;
    private List<Configuration> configurationList;
    private int price;
    private String productionYear;

    public Auction(String title, int price, String productionYear) {
        this.title = title;
        this.carList = new ArrayList<>();
        this.configurationList = new ArrayList<>();
        this.price = price;
        this.productionYear = productionYear;
    }

    public Auction() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
    }

    public List<Configuration> getConfigurationList() {
        return configurationList;
    }

    public void setConfigurationList(List<Configuration> configurationList) {
        this.configurationList = configurationList;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getProductionYear() {
        return productionYear;
    }

    public void setProductionYear(String productionYear) {
        this.productionYear = productionYear;
    }
}
