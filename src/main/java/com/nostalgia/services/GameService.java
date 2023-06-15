package com.nostalgia.services;

import com.nostalgia.PluginLoader;
import com.nostalgia.utils.NPlayer;
import org.bukkit.entity.Player;

public class GameService {

    public static boolean isPlayerAlive(Player p) {
        boolean isAlive = getNPlayer(p) != null;

        if (isAlive)
            isAlive = getNPlayer(p).isAlive();
        return isAlive;
    }

    public static NPlayer getNPlayer(Player p) {
        NPlayer nPlayer = null;

        for (NPlayer player : PluginLoader.getInstance().getManager().getPlayers())
            if (player.getName().equals(p.getName())) nPlayer = player;
        return nPlayer;
    }
}
