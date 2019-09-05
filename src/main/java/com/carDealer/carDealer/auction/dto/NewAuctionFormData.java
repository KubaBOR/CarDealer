package com.carDealer.carDealer.auction.dto;

public class NewAuctionFormData {

    private String description;
    private String car;
    private String[] configurations;
    private int milleageKm;
    private int price;
    private String productionYear;

    private String bid;

    public NewAuctionFormData() {
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

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String[] getConfigurations() {
        return configurations;
    }

    public void setConfigurations(String[] configurations) {
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

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }
}
