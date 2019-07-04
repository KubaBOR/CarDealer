package com.championships.Championships.configuration.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "configurations")
public class ConfigurationDocument {
    private String id;

    private String addon;

    public ConfigurationDocument() {
    }

    public ConfigurationDocument(String addon) {
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

    @Override
    public String toString() {
        return "ConfigurationDocument{" +
                "id='" + id + '\'' +
                ", addon='" + addon + '\'' +
                '}';
    }
}
