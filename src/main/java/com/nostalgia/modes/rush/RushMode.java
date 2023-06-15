package com.nostalgia.modes.rush;

import com.nostalgia.Minigame;
import org.bukkit.ChatColor;

public class RushMode extends Minigame {

    @Override
    public void setDefaults() {
        setPlayerLimit(22);
        setMinPlayers(2);
        setTeamSize(2);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Rush" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }
}
