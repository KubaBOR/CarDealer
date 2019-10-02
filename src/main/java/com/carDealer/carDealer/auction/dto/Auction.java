package com.carDealer.carDealer.auction.dto;

import com.carDealer.carDealer.bid.dto.Bid;
import com.carDealer.carDealer.cars.dto.Car;
import com.carDealer.carDealer.configuration.dto.Configuration;
import com.carDealer.carDealer.photos.model.Photo;

import java.util.List;

public class Auction {

    private String id;
    private String description;
    private Car car;
    private List<Configuration> configurations;
    private int milleageKm;

    private int price;
    private String productionYear;

    private List<Bid> biddingList;
    private Photo photo;

    public Auction() {}

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

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<Configuration> configurations) {
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
