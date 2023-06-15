package com.nostalgia;

import com.nostalgia.messages.Message;
import com.nostalgia.messages.Messages;
import com.nostalgia.utils.Items;
import com.nostalgia.utils.NPlayer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Minigame {

    private int playerLimit;
    private int minPlayers;
    private String prefix;
    private int teamSize;
    private int lives = -1;
    private int countdownPreLobby;
    private BukkitTask preLobbyTask;
    private int countdownLobby = 120;
    private BukkitTask lobbyTask;
    private Messages messages;

    public Minigame() {
        messages = new Messages();
        registerListeners();
        setDefaults();
    }

    public void registerListeners() {
        //register events (override)
    }

    public void startGame() {
        //start game (override)
    }

    public void startLobby() {
        startPreLobby();
    }

    public void startPreLobby() {
        // Check every 15 seconds if there are enough players to start the game
        preLobbyTask = Bukkit.getScheduler().runTaskTimerAsynchronously(PluginLoader.getInstance(), () -> {
            if (Bukkit.getOnlinePlayers().size() < minPlayers) {
                Bukkit.broadcastMessage(prefix + "Waiting for more players to join..");
            } else {
                startLobbyCountdown();
                preLobbyTask.cancel();
            }
        }, 0L, 300L);
    }

    public void startLobbyCountdown() {
        lobbyTask = Bukkit.getScheduler().runTaskTimerAsynchronously(PluginLoader.getInstance(), () -> {
            if (countdownLobby == 0) {
                startGame();
                lobbyTask.cancel();
            } else {
                if (Bukkit.getOnlinePlayers().size() < minPlayers) {
                    for (NPlayer p : PluginLoader.getInstance().getManager().getPlayers())
                        p.getPlayer().sendMessage(prefix + messages.get(Message.NOT_ENOUGH_PLAYERS, p.getPreferences().getLang()));
                    countdownLobby = 120;
                    lobbyTask.cancel();
                    startPreLobby();
                }
                for (Player p : Bukkit.getOnlinePlayers())
                    p.setLevel(countdownLobby);
                if (countdownLobby == 60 || countdownLobby == 30 || countdownLobby == 15 || countdownLobby == 10 || countdownLobby <= 5) {
                    for (NPlayer p : PluginLoader.getInstance().getManager().getPlayers()) {
                        String message = messages.get(Message.GAME_COUNTDOWN, p.getPreferences().getLang()).replace("%s%", String.valueOf(countdownLobby));
                        p.getPlayer().sendMessage(prefix + message);
                    }
                }
                if (countdownLobby >= 5) {
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        p.sendTitle(String.valueOf(ChatColor.GOLD) + countdownLobby, null, 0, 20, 0);
                        p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 1.0F, 1.0F);
                    }
                }
                countdownLobby--;
            }
        }, 0L, 20L);
    }

    public void setDefaults() {
        setPlayerLimit(12);
        setMinPlayers(2);
        setTeamSize(1);
        setPrefix(String.valueOf(ChatColor.BLUE) + ChatColor.BOLD + "Game" + ChatColor.GRAY + " ↠ " + ChatColor.RESET);
    }

    public int getPlayerLimit() {
        return playerLimit;
    }

    public void setPlayerLimit(int playerLimit) {
        this.playerLimit = playerLimit;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        this.minPlayers = minPlayers;
    }

    public int getLives() {
        return lives;
    }

    public void getLobbyItems(Player p) {
        ItemStack lobbyItem = Items.create(String.valueOf(ChatColor.YELLOW) + "Return to lobby", Material.COMPASS, 1, null);
        ItemStack teamSelectorItem = Items.create(String.valueOf(ChatColor.BLUE) + "Select a team", Material.COMPASS, 1, null);

        if(teamSize > 1)
            p.getInventory().setItem(0, teamSelectorItem);
        p.getInventory().setItem(8, lobbyItem);
    }

    public Messages getMessages() {
        return messages;
    }
}
