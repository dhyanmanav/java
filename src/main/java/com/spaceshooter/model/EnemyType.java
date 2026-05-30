package com.spaceshooter.model;

public enum EnemyType {
    DRONE("Drone Swarm"),
    RAIDER("Void Raider"),
    DESTROYER("Star Destroyer"),
    DREADNOUGHT("Dreadnought");

    private final String label;

    EnemyType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
