package com.spaceshooter.model;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GameState {
    private final List<Pilot> pilots;
    private final List<Ship> ships;
    private final List<Mission> missions;
    private final List<Enemy> nextWave;
    private final List<ScoreEntry> leaderboard;
    private final Set<String> unlockedUpgrades;
    private final Map<String, Integer> weaponStock;
    private final String difficultySummary;
    private final String nextWaveTactic;

    public GameState(List<Pilot> pilots, List<Ship> ships, List<Mission> missions, List<Enemy> nextWave,
                     List<ScoreEntry> leaderboard, Set<String> unlockedUpgrades, Map<String, Integer> weaponStock,
                     String difficultySummary, String nextWaveTactic) {
        this.pilots = pilots;
        this.ships = ships;
        this.missions = missions;
        this.nextWave = nextWave;
        this.leaderboard = leaderboard;
        this.unlockedUpgrades = unlockedUpgrades;
        this.weaponStock = weaponStock;
        this.difficultySummary = difficultySummary;
        this.nextWaveTactic = nextWaveTactic;
    }

    public List<Pilot> getPilots() {
        return pilots;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public List<Enemy> getNextWave() {
        return nextWave;
    }

    public List<ScoreEntry> getLeaderboard() {
        return leaderboard;
    }

    public Set<String> getUnlockedUpgrades() {
        return unlockedUpgrades;
    }

    public Map<String, Integer> getWeaponStock() {
        return weaponStock;
    }

    public String getDifficultySummary() {
        return difficultySummary;
    }

    public String getNextWaveTactic() {
        return nextWaveTactic;
    }
}
