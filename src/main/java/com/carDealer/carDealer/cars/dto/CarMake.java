package com.carDealer.carDealer.cars.dto;

public enum CarMake {
    AUDI("Audi"),
    BMW("BMW"),
    CITROEN("Citroen"),
    FORD("Ford"),
    HONDA("Honda");

    private final String displayValue;

    CarMake(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}
