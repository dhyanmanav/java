package com.spaceshooter.model;

public class Enemy {
    private final String name;
    private final EnemyType type;
    private final int threatLevel;

    public Enemy(String name, EnemyType type, int threatLevel) {
        this.name = name;
        this.type = type;
        this.threatLevel = threatLevel;
    }

    public String getName() {
        return name;
    }

    public EnemyType getType() {
        return type;
    }

    public int getThreatLevel() {
        return threatLevel;
    }
}
