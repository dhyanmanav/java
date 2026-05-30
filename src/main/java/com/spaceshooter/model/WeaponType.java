package com.spaceshooter.model;

public enum WeaponType {
    LASER("Laser"),
    PLASMA("Plasma"),
    RAILGUN("Railgun"),
    EMP("EMP");

    private final String label;

    WeaponType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
