package com.carDealer.carDealer.cars.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document (collection = "make")
public class MakeDocument {

    @Id
    private String id;

    private String name;

    public MakeDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "MakeDocument{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
