package com.spaceshooter.model;

public class Pilot {
    private final String name;
    private final String callsign;
    private final PilotRank rank;
    private final int stamina;
    private final int accuracy;
    private final int agility;

    public Pilot(String name, String callsign, PilotRank rank, int stamina, int accuracy, int agility) {
        this.name = name;
        this.callsign = callsign;
        this.rank = rank;
        this.stamina = stamina;
        this.accuracy = accuracy;
        this.agility = agility;
    }

    public String getName() {
        return name;
    }

    public String getCallsign() {
        return callsign;
    }

    public PilotRank getRank() {
        return rank;
    }

    public int getStamina() {
        return stamina;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getAgility() {
        return agility;
    }
}
