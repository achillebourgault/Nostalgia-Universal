package com.nostalgia.modes.hungergame;

import com.nostalgia.Minigame;
import org.bukkit.ChatColor;

public class HungerGameMode extends Minigame {

    @Override
    public void setDefaults() {
        setPlayerLimit(22);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Hunger Game" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }
}
