package com.nostalgia.modes.towers;

import com.nostalgia.Minigame;
import org.bukkit.ChatColor;

public class TowersMode extends Minigame {

    @Override
    public void setDefaults() {
        setPlayerLimit(22);
        setMinPlayers(2);
        setTeamSize(2);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Towers" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }
}
