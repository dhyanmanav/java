package com.spaceshooter.model;

public class ScoreEntry {
    private final String pilotName;
    private final int score;
    private final int missionsCleared;

    public ScoreEntry(String pilotName, int score, int missionsCleared) {
        this.pilotName = pilotName;
        this.score = score;
        this.missionsCleared = missionsCleared;
    }

    public String getPilotName() {
        return pilotName;
    }

    public int getScore() {
        return score;
    }

    public int getMissionsCleared() {
        return missionsCleared;
    }
}
