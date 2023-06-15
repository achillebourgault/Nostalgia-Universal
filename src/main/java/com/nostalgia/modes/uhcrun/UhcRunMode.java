package com.nostalgia.modes.uhcrun;

import com.nostalgia.Minigame;
import com.nostalgia.PluginLoader;
import com.nostalgia.modes.uhcrun.events.GameModeEvent;
import org.bukkit.ChatColor;

public class UhcRunMode extends Minigame {

    @Override
    public void setDefaults() {
        System.out.println("[GameModeWorker] Loading UHCRun Mode..");
        setPlayerLimit(22);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "UHCRUN" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }

    @Override
    public void registerListeners() {
        PluginLoader.getInstance().getServer().getPluginManager().registerEvents(new GameModeEvent(), PluginLoader.getInstance());
    }
}
