package com.nostalgia.modes.speedrun;

import com.nostalgia.Minigame;
import org.bukkit.ChatColor;

public class SpeedRunMode extends Minigame {

    @Override
    public void setDefaults() {
        setPlayerLimit(8);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "SpeedRun" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }
}
