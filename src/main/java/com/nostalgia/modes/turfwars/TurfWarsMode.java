package com.nostalgia.modes.turfwars;

import com.nostalgia.Minigame;
import org.bukkit.ChatColor;

public class TurfWarsMode extends Minigame {

    @Override
    public void setDefaults() {
        setPlayerLimit(22);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Turf Wars" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }
}
