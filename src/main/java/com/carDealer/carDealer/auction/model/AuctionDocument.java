package com.carDealer.carDealer.auction.model;

import com.carDealer.carDealer.cars.dto.Car;
import com.carDealer.carDealer.configuration.dto.Configuration;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "auction")
public class AuctionDocument {

    @Id
    private String id;
    private String title;
    private Car car;
    private List<Configuration> configurationList;
    private int price;
    private String productionYear;

    public AuctionDocument() { }

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
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

