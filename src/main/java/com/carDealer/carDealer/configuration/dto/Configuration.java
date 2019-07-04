package com.carDealer.carDealer.configuration.dto;

public class Configuration {

    private String id;
    private String addon;

    public Configuration() {
    }

    public Configuration(String addon) {
        this.addon = addon;
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
}
