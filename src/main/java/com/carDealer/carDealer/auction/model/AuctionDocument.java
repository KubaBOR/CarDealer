package com.carDealer.carDealer.auction.model;

import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "auction")
public class AuctionDocument {

    @Id
    private String id;
    private String title;
    private CarDocument car;
    private String configurationId;
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

    public CarDocument getCar() {
        return car;
    }

    public void setCar(CarDocument car) {
        this.car = car;
    }

    public String getConfigurationId() {
        return configurationId;
    }

    public void setConfigurationId(String configurationId) {
        this.configurationId = configurationId;
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

    @Override
    public String toString() {
        return "AuctionDocument{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", car=" + car +
                ", configurationId='" + configurationId + '\'' +
                ", price=" + price +
                ", productionYear='" + productionYear + '\'' +
                '}';
    }
}

