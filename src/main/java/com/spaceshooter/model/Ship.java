package com.spaceshooter.model;

import java.util.Set;

public class Ship {
    private final String callsign;
    private final ShipClass shipClass;
    private final WeaponType primaryWeapon;
    private final int shield;
    private final int speed;
    private final int hull;
    private final Set<WeaponType> loadout;

    public Ship(String callsign, ShipClass shipClass, WeaponType primaryWeapon, int shield, int speed, int hull,
                Set<WeaponType> loadout) {
        this.callsign = callsign;
        this.shipClass = shipClass;
        this.primaryWeapon = primaryWeapon;
        this.shield = shield;
        this.speed = speed;
        this.hull = hull;
        this.loadout = loadout;
    }

    public String getCallsign() {
        return callsign;
    }

    public ShipClass getShipClass() {
        return shipClass;
    }

    public WeaponType getPrimaryWeapon() {
        return primaryWeapon;
    }

    public int getShield() {
        return shield;
    }

    public int getSpeed() {
        return speed;
    }

    public int getHull() {
        return hull;
    }

    public Set<WeaponType> getLoadout() {
        return loadout;
    }
}
