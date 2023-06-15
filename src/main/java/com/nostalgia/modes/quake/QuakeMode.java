package com.nostalgia.modes.quake;

import com.nostalgia.Minigame;
import org.bukkit.ChatColor;

public class QuakeMode extends Minigame {

    @Override
    public void setDefaults() {
        setPlayerLimit(12);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Quake" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }
}
