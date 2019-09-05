package com.carDealer.carDealer.configuration.dto;

public class Configuration {

    private String id;
    private String addon;
    private int price;

    public Configuration() {
    }

    public Configuration(String addon, int price) {
        this.addon = addon;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddon() {
        return addon;
    }

    public void setAddon(String addon) {
        this.addon = addon;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
