package com.spaceshooter.model;

public enum Difficulty {
    ROOKIE("Rookie"),
    VETERAN("Veteran"),
    ELITE("Elite"),
    LEGEND("Legend");

    private final String label;

    Difficulty(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
