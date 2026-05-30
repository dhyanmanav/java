package com.spaceshooter.model;

public enum ShipClass {
    INTERCEPTOR("Interceptor"),
    BOMBER("Bomber"),
    FRIGATE("Frigate"),
    CRUISER("Cruiser");

    private final String label;

    ShipClass(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
