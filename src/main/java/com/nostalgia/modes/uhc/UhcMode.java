package com.nostalgia.modes.uhc;

import com.nostalgia.Minigame;
import com.nostalgia.PluginLoader;
import com.nostalgia.modes.uhc.events.GameModeEvent;
import org.bukkit.ChatColor;

public class UhcMode extends Minigame {

    @Override
    public void setDefaults() {
        System.out.println("[GameModeWorker] Loading UHC Mode..");
        setPlayerLimit(22);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "UHC" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }

    @Override
    public void registerListeners() {
        PluginLoader.getInstance().getServer().getPluginManager().registerEvents(new GameModeEvent(), PluginLoader.getInstance());
    }
}
