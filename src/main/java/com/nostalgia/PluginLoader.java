package com.nostalgia;

import com.nostalgia.events.GameEvents;
import com.nostalgia.managers.ConfigManager;
import com.nostalgia.managers.GameManager;
import com.nostalgia.utils.MinigameType;
import org.bukkit.plugin.java.JavaPlugin;
public final class PluginLoader extends JavaPlugin {

    public static PluginLoader main;
    private ConfigManager configManager;
    private GameManager gameManager;

    @Override
    public void onEnable() {
        main = this;
        this.configManager = new ConfigManager();
        MinigameType modeType = getModeType();
        this.gameManager = new GameManager(MinigameType.valueOf(configManager.getModeType()));
        registerListeners();
    }

    public MinigameType getModeType() {
        switch (configManager.getModeType()) {
            case "HUNGERGAME":
                return MinigameType.HUNGERGAME;
            case "QUAKE":
                return MinigameType.QUAKE;
            case "SPEEDRUN":
                return MinigameType.SPEEDRUN;
            case "RUSH":
                return MinigameType.RUSH;
            case "TOWER":
                return MinigameType.TOWER;
            case "TURFWARS":
                return MinigameType.TURFWARS;
            case "UHCRUN":
                return MinigameType.UHCRUN;
            case "UHC":
                return MinigameType.UHC;
            default:
                return MinigameType.NONE;
        }
    }

    public void registerListeners() {
        getServer().getPluginManager().registerEvents(new GameEvents(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static PluginLoader getInstance() {
        return main;
    }

    public GameManager getManager() {
        return gameManager;
    }
}
