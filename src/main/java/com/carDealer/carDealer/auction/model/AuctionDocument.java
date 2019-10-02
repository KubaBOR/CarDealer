package com.carDealer.carDealer.auction.model;

import com.carDealer.carDealer.bid.dto.Bid;
import com.carDealer.carDealer.cars.model.CarDocument;
import com.carDealer.carDealer.configuration.model.ConfigurationDocument;
import com.carDealer.carDealer.photos.model.Photo;
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
    private int milleageKm;
    private int price;
    private String productionYear;

    private List<Bid> biddingList;
    private Photo photo;

    public AuctionDocument() { }

    public AuctionDocument(String description, CarDocument car, List<ConfigurationDocument> configurations, int milleageKm, int price, String productionYear, List<Bid> biddingList, Photo photo) {
        this.description = description;
        this.car = car;
        this.configurations = configurations;
        this.milleageKm = milleageKm;
        this.price = price;
        this.productionYear = productionYear;
        this.biddingList = biddingList;
        this.photo = photo;
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

    public int getMilleageKm() {
        return milleageKm;
    }

    public void setMilleageKm(int milleageKm) {
        this.milleageKm = milleageKm;
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

    public List<Bid> getBiddingList() {
        return biddingList;
    }

    public void setBiddingList(List<Bid> biddingList) {
        this.biddingList = biddingList;
    }

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
}

