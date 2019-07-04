package com.championships.Championships.auction.model;

import com.championships.Championships.cars.dto.Car;
import com.championships.Championships.cars.model.CarDocument;
import com.championships.Championships.configuration.dto.Configuration;
import com.championships.Championships.configuration.model.ConfigurationDocument;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "auction")
public class AuctionDocument {

    @Id
    private String id;
    private String title;
    private List<CarDocument> carList;
    private List<ConfigurationDocument> configurationList;
    private int price;
    private String productionYear;

    public AuctionDocument() { }

    public AuctionDocument(String id, String title, List<CarDocument> carList, List<ConfigurationDocument> configurationList, int price, String productionYear) {
        this.id = id;
        this.title = title;
        this.carList = carList;
        this.configurationList = configurationList;
        this.price = price;
        this.productionYear = productionYear;
    }

    public String getId() {
        return id;
    }

    public List<CarDocument> getCarList() {
        return carList;
    }

    public void setCarList(List<CarDocument> carList) {
        this.carList = carList;
    }

    public List<ConfigurationDocument> getConfigurationList() {
        return configurationList;
    }

    public void setConfigurationList(List<ConfigurationDocument> configurationList) {
        this.configurationList = configurationList;
    }

    @Override
    public String toString() {
        return "AuctionDocument{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", carList=" + carList +
                ", configurationList=" + configurationList +
                ", price=" + price +
                ", productionYear='" + productionYear + '\'' +
                '}';
    }
}

