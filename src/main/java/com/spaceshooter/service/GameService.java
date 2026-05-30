package com.spaceshooter.service;

import com.spaceshooter.model.Difficulty;
import com.spaceshooter.model.Enemy;
import com.spaceshooter.model.EnemyType;
import com.spaceshooter.model.GameState;
import com.spaceshooter.model.Mission;
import com.spaceshooter.model.Pilot;
import com.spaceshooter.model.PilotRank;
import com.spaceshooter.model.ScoreEntry;
import com.spaceshooter.model.Ship;
import com.spaceshooter.model.ShipClass;
import com.spaceshooter.model.WeaponType;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeSet;

@Service
public class GameService {
    private final List<Pilot> pilots = new ArrayList<>();
    private final Map<String, Ship> shipsByCallsign = new LinkedHashMap<>();
    private final Set<String> unlockedUpgrades = new LinkedHashSet<>();
    private final Deque<Enemy> enemyWaveQueue = new ArrayDeque<>();
    private final Deque<String> tacticalStack = new ArrayDeque<>();
    private final PriorityQueue<ScoreEntry> leaderboard =
            new PriorityQueue<>(Comparator.comparingInt(ScoreEntry::getScore).reversed());
    private final EnumMap<Difficulty, Integer> difficultyHullBoost = new EnumMap<>(Difficulty.class);
    private final EnumMap<WeaponType, Integer> weaponStock = new EnumMap<>(WeaponType.class);
    private final EnumSet<WeaponType> signatureWeapons = EnumSet.of(WeaponType.RAILGUN, WeaponType.EMP);
    private final Set<String> missionCodes = new TreeSet<>();
    private final List<Mission> missions = new ArrayList<>();

    @PostConstruct
    public void init() {
        pilots.add(new Pilot("Ava Lin", "Comet", PilotRank.ACE, 92, 88, 90));
        pilots.add(new Pilot("Ravi Kael", "Nova", PilotRank.COMMANDER, 96, 85, 82));
        pilots.add(new Pilot("Mira Jax", "Pulse", PilotRank.CADET, 78, 80, 86));
        pilots.add(new Pilot("Kenzo Voss", "Orion", PilotRank.LEGEND, 98, 93, 91));

        shipsByCallsign.put("Valkyrie", new Ship(
                "Valkyrie",
                ShipClass.INTERCEPTOR,
                WeaponType.LASER,
                72,
                94,
                65,
                EnumSet.of(WeaponType.LASER, WeaponType.PLASMA)
        ));
        shipsByCallsign.put("Bulwark", new Ship(
                "Bulwark",
                ShipClass.CRUISER,
                WeaponType.RAILGUN,
                90,
                70,
                92,
                EnumSet.of(WeaponType.RAILGUN, WeaponType.EMP)
        ));
        shipsByCallsign.put("Specter", new Ship(
                "Specter",
                ShipClass.FRIGATE,
                WeaponType.PLASMA,
                80,
                82,
                78,
                EnumSet.of(WeaponType.PLASMA, WeaponType.LASER)
        ));
        shipsByCallsign.put("Griffin", new Ship(
                "Griffin",
                ShipClass.BOMBER,
                WeaponType.EMP,
                88,
                66,
                89,
                EnumSet.of(WeaponType.EMP, WeaponType.RAILGUN)
        ));

        unlockedUpgrades.add("Overcharged Thrusters");
        unlockedUpgrades.add("Reactive Shield Lining");
        unlockedUpgrades.add("Quantum Cooling");
        unlockedUpgrades.add("Phase Cloak");

        enemyWaveQueue.add(new Enemy("Redveil Swarm", EnemyType.DRONE, 3));
        enemyWaveQueue.add(new Enemy("Vortex Raider", EnemyType.RAIDER, 6));
        enemyWaveQueue.add(new Enemy("Helios Destroyer", EnemyType.DESTROYER, 8));
        enemyWaveQueue.add(new Enemy("Umbra Dreadnought", EnemyType.DREADNOUGHT, 10));

        tacticalStack.push("EMP burst then flank the Dreadnought.");
        tacticalStack.push("Use plasma arcs to break shield clusters.");
        tacticalStack.push("Escort the bomber wing through the asteroid ring.");

        missionCodes.add("NEBULA-RUN");
        missionCodes.add("STARFALL");
        missionCodes.add("ORBITAL-SHIELD");

        missions.add(new Mission("Nebula Run", Difficulty.ROOKIE, "Evade debris while mapping the rift.",
                1200, 180));
        missions.add(new Mission("Starfall", Difficulty.VETERAN, "Intercept raiders before they reach the colony.",
                2400, 240));
        missions.add(new Mission("Orbital Shield", Difficulty.ELITE, "Protect the relay tower from a siege wave.",
                3600, 300));

        difficultyHullBoost.put(Difficulty.ROOKIE, 5);
        difficultyHullBoost.put(Difficulty.VETERAN, 12);
        difficultyHullBoost.put(Difficulty.ELITE, 18);
        difficultyHullBoost.put(Difficulty.LEGEND, 25);

        weaponStock.put(WeaponType.LASER, 120);
        weaponStock.put(WeaponType.PLASMA, 70);
        weaponStock.put(WeaponType.RAILGUN, 40);
        weaponStock.put(WeaponType.EMP, 30);

        leaderboard.add(new ScoreEntry("Comet", 12840, 6));
        leaderboard.add(new ScoreEntry("Orion", 15620, 7));
        leaderboard.add(new ScoreEntry("Nova", 11110, 5));
        leaderboard.add(new ScoreEntry("Pulse", 9040, 4));
    }

    public GameState buildGameState() {
        List<Ship> ships = new ArrayList<>(shipsByCallsign.values());
        List<Enemy> nextWave = new ArrayList<>();
        int waveCount = 0;
        for (Enemy enemy : enemyWaveQueue) {
            nextWave.add(enemy);
            waveCount++;
            if (waveCount == 3) {
                break;
            }
        }

        List<ScoreEntry> orderedScores = new ArrayList<>(leaderboard);
        orderedScores.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());

        return new GameState(
                new ArrayList<>(pilots),
                ships,
                new ArrayList<>(missions),
                nextWave,
                orderedScores,
                new LinkedHashSet<>(unlockedUpgrades),
                toWeaponStockMap(),
                buildDifficultySummary(),
                selectNextWaveTactic()
        );
    }

    public List<Ship> getShips() {
        return new ArrayList<>(shipsByCallsign.values());
    }

    public Set<String> getUnlockedUpgrades() {
        return new LinkedHashSet<>(unlockedUpgrades);
    }

    private Map<String, Integer> toWeaponStockMap() {
        Map<String, Integer> stock = new LinkedHashMap<>();
        for (Map.Entry<WeaponType, Integer> entry : weaponStock.entrySet()) {
            stock.put(entry.getKey().getLabel(), entry.getValue());
        }
        return stock;
    }

    private String buildDifficultySummary() {
        int averageStamina = (int) pilots.stream().mapToInt(Pilot::getStamina).average().orElse(0);
        Difficulty difficulty;
        if (averageStamina >= 92) {
            difficulty = Difficulty.LEGEND;
        } else if (averageStamina >= 85) {
            difficulty = Difficulty.ELITE;
        } else if (averageStamina >= 75) {
            difficulty = Difficulty.VETERAN;
        } else {
            difficulty = Difficulty.ROOKIE;
        }

        int hullBoost = difficultyHullBoost.getOrDefault(difficulty, 0);
        String badge;
        switch (difficulty) {
            case LEGEND:
                badge = "Nebula Elite";
                break;
            case ELITE:
                badge = "Star Vanguard";
                break;
            case VETERAN:
                badge = "Frontline Core";
                break;
            default:
                badge = "Training Wing";
        }
        return difficulty.getLabel() + " / +" + hullBoost + "% hull (" + badge + ")";
    }

    private String selectNextWaveTactic() {
        boolean hasDreadnought = enemyWaveQueue.stream().anyMatch(enemy -> enemy.getType() == EnemyType.DREADNOUGHT);
        if (hasDreadnought && signatureWeapons.contains(WeaponType.EMP)) {
            return "Disrupt the Dreadnought with EMP, then strike the rear vents.";
        }
        if (!tacticalStack.isEmpty()) {
            return tacticalStack.peek();
        }
        return "Hold formation and focus fire.";
    }
}
