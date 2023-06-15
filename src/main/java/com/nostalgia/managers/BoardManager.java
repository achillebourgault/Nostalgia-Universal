package com.nostalgia.managers;

import com.nostalgia.PluginLoader;
import com.nostalgia.messages.Message;
import com.nostalgia.utils.NPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class BoardManager {

    private Scoreboard lobbyBoard;
    private Objective lobbyObjective;

    public BoardManager() {
        initLobbyBoard();
        initBoardTask();
    }

    public void initBoardTask() {
        Bukkit.getScheduler().runTaskTimerAsynchronously(PluginLoader.getInstance(), () -> {
            switch(PluginLoader.getInstance().getManager().getState()) {
                case LOBBY:
                    drawLobbyBoard();
                    break;
            }
            drawLobbyBoard();
        }, 0L, 20L);
    }
    public void clearLobbyBoard() {
        lobbyBoard = Objects.requireNonNull(Bukkit.getScoreboardManager()).getNewScoreboard();
        if (lobbyObjective != null) lobbyObjective.unregister();
        lobbyObjective = lobbyBoard.registerNewObjective("lobby", "dummy", "§6§lNostalgia");
    }

    public void initLobbyBoard() {
    }

    public void drawLobbyBoard() {
        for (NPlayer p : PluginLoader.getInstance().getManager().getPlayers()) {
            lobbyBoard = Bukkit.getScoreboardManager().getNewScoreboard();
            lobbyObjective = lobbyBoard.registerNewObjective("lobby", String.valueOf(ChatColor.GOLD) + ChatColor.BOLD + PluginLoader.getInstance().getManager().getGame().getPrefix());
            lobbyObjective.setDisplaySlot(DisplaySlot.SIDEBAR);


            lobbyObjective.getScore("").setScore(0);
            lobbyObjective.getScore(ChatColor.WHITE + PluginLoader.getInstance().getManager().getGame().getMessages().get(Message.WAITING_PLAYERS, p.getPreferences().getLang())).setScore(1);
            lobbyObjective.getScore(" ").setScore(2);
            p.getPlayer().setScoreboard(lobbyBoard);
        }
    }
}
