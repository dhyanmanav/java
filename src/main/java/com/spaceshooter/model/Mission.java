package com.spaceshooter.model;

public class Mission {
    private final String codeName;
    private final Difficulty difficulty;
    private final String objective;
    private final int rewardCredits;
    private final int timeLimitSeconds;

    public Mission(String codeName, Difficulty difficulty, String objective, int rewardCredits, int timeLimitSeconds) {
        this.codeName = codeName;
        this.difficulty = difficulty;
        this.objective = objective;
        this.rewardCredits = rewardCredits;
        this.timeLimitSeconds = timeLimitSeconds;
    }

    public String getCodeName() {
        return codeName;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public String getObjective() {
        return objective;
    }

    public int getRewardCredits() {
        return rewardCredits;
    }

    public int getTimeLimitSeconds() {
        return timeLimitSeconds;
    }
}
