package com.nostalgia.utils;

import com.nostalgia.PlayerPreferences;
import com.nostalgia.Statistics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.UUID;

public class NPlayer {

    private UUID playerUUID;
    private String playerName;
    private Statistics statistics;
    private PlayerPreferences preferences;
    private boolean alive = true;

    public NPlayer(String playerName) {
        this.playerName = playerName;
        if (Bukkit.getPlayer(playerName) != null) {
            this.playerUUID = Objects.requireNonNull(Bukkit.getPlayer(playerName)).getUniqueId();
            this.statistics = new Statistics();
            this.preferences = new PlayerPreferences();
        }
    }

    public void fetchPlayerData() {
        this.playerUUID = Bukkit.getPlayer(playerName) != null ?
                Objects.requireNonNull(Bukkit.getPlayer(playerName)).getUniqueId() : null;
        this.statistics = new Statistics();

        //TODO: Fetch API to retrieve player data like cosmetics or bought items
    }

    public UUID getUUID() {
        return playerUUID;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public String getName() {
        return playerName;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(playerName);
    }

    public PlayerPreferences getPreferences() {
        return preferences;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }
}
