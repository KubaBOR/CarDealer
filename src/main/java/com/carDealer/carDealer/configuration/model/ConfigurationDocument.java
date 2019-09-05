package com.carDealer.carDealer.configuration.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configurations")
public class ConfigurationDocument {
    private String id;

    private String addon;
    private int price;

    public ConfigurationDocument() {
    }

    public ConfigurationDocument(String addon, int price) {
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

    @Override
    public String toString() {
        return "ConfigurationDocument{" +
                "id='" + id + '\'' +
                ", addon='" + addon + '\'' +
                ", price=" + price +
                '}';
    }
}
