package com.carDealer.carDealer.auction.model;

import com.carDealer.carDealer.cars.dto.Car;
import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.configuration.dto.Configuration;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "auction")
public class AuctionDocument {

    @Id
    private String id;
    private String description;
    private CarDocument car;

    private List<ConfigurationDocument> configurations;
    private int price;
    private String productionYear;

    public AuctionDocument() { }

    public AuctionDocument(String description, CarDocument car, List<ConfigurationDocument> configurations, int price, String productionYear) {
        this.description = description;
        this.car = car;
        this.configurations = configurations;
        this.price = price;
        this.productionYear = productionYear;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public CarDocument getCar() {
        return car;
    }

    public void setCar(CarDocument car) {
        this.car = car;
    }

    public List<ConfigurationDocument> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<ConfigurationDocument> configurations) {
        this.configurations = configurations;
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

