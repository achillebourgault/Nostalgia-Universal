package com.nostalgia.managers;

import com.nostalgia.Minigame;
import com.nostalgia.PluginLoader;
import com.nostalgia.modes.hungergame.HungerGameMode;
import com.nostalgia.modes.quake.QuakeMode;
import com.nostalgia.modes.rush.RushMode;
import com.nostalgia.modes.speedrun.SpeedRunMode;
import com.nostalgia.modes.towers.TowersMode;
import com.nostalgia.modes.turfwars.TurfWarsMode;
import com.nostalgia.modes.uhc.*;
import com.nostalgia.modes.uhcrun.UhcRunMode;
import com.nostalgia.utils.GameState;
import com.nostalgia.utils.MinigameType;
import com.nostalgia.utils.NPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GameManager {
    private final MinigameType gameModeType;
    private Minigame game;
    private GameState gameState;
    private ArrayList<NPlayer> players = new ArrayList<>();

    public GameManager(MinigameType gameModeType) {
        this.gameModeType = gameModeType;
        this.gameState = GameState.LOBBY;
        initializeGamemode();
    }

    public void initializeGamemode() {
        switch (gameModeType) {
            case HUNGERGAME:
                this.game = new HungerGameMode();
                break;
            case QUAKE:
                this.game = new QuakeMode();
                break;
            case SPEEDRUN:
                this.game = new SpeedRunMode();
                break;
            case RUSH:
                this.game = new RushMode();
                break;
            case TOWER:
                this.game = new TowersMode();
                break;
            case TURFWARS:
                this.game = new TurfWarsMode();
                break;
            case UHCRUN:
                this.game = new UhcRunMode();
                break;
            case UHC:
                this.game = new UhcMode();
                break;
        }

    }

    public MinigameType getGameModeType() {
        return gameModeType;
    }

    public GameState getState() {
        return gameState;
    }

    public void setState(GameState state) {
        this.gameState = state;
    }

    public Minigame getGame() {
        return game;
    }

    public void setGame(Minigame game) {
        this.game = game;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void registerPlayer(String playername) {
        players.add(new NPlayer(playername));
    }

    public void unregisterPlayer(String playername) {
        players.remove(playername);
    }

    public ArrayList<NPlayer> getPlayers() {
        return players;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public void eliminatePlayer(NPlayer nPlayer) {
        Player p = Bukkit.getPlayer(nPlayer.getName());

        nPlayer.setAlive(false);
        if (p != null && p.isOnline()) {
            p.sendMessage(game.getPrefix()+ "You have been eliminated from the game!");
            //TODO:
        }
    }
}
