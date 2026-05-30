package com.spaceshooter.model;

public enum PilotRank {
    CADET("Cadet"),
    ACE("Ace"),
    COMMANDER("Commander"),
    LEGEND("Legend");

    private final String label;

    PilotRank(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
