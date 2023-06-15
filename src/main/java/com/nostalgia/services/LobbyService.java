package com.nostalgia.services;

import com.nostalgia.PluginLoader;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class LobbyService {

    public static void appendLobbyEffect(Player p) {
        p.setGameMode(GameMode.ADVENTURE);
        p.setExp(0);
        p.setFoodLevel(20);
        p.setHealth(20);
        p.getInventory().clear();
        PluginLoader.getInstance().getManager().getGame().getLobbyItems(p);
    }
}
