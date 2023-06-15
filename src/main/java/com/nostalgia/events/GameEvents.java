package com.nostalgia.events;

import com.nostalgia.Minigame;
import com.nostalgia.managers.GameManager;
import com.nostalgia.PluginLoader;
import com.nostalgia.messages.Message;

import com.nostalgia.services.LobbyService;
import com.nostalgia.utils.GameState;
import com.nostalgia.utils.NPlayer;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import static com.nostalgia.services.GameService.getNPlayer;

public class GameEvents implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        Minigame game = PluginLoader.getInstance().getManager().getGame();

        if (PluginLoader.getInstance().getManager().getGameState().equals(GameState.LOBBY)) {
            if (PluginLoader.getInstance().getManager().getPlayers().size() >= PluginLoader.getInstance().getManager().getGame().getPlayerLimit()) {
                System.out.println(PluginLoader.getInstance().getManager().getPlayers().size());
                System.out.println(PluginLoader.getInstance().getManager().getGame().getPlayerLimit());
                e.getPlayer().kickPlayer(ChatColor.RED + "The game is full.");
                return;
            }
            PluginLoader.getInstance().getManager().registerPlayer(p.getName());
            LobbyService.appendLobbyEffect(p);
            p.playSound(p.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1, 1);
            e.setJoinMessage(null);
            for (NPlayer player : PluginLoader.getInstance().getManager().getPlayers()) {
                player.getPlayer().sendMessage(game.getPrefix() + game.getMessages().get(Message.PLAYER_JOIN, getNPlayer(p).getPreferences().getLang()).replace("%player%", p.getName())
                        + ChatColor.GRAY + "[" + ChatColor.BLUE + PluginLoader.getInstance().getManager().getPlayers().size()
                        + ChatColor.GRAY + "/"
                        + ChatColor.BLUE + PluginLoader.getInstance().getManager().getGame().getPlayerLimit() + ChatColor.GRAY + "]");
            }
        } else {
            e.setJoinMessage(null);
            for (NPlayer player : PluginLoader.getInstance().getManager().getPlayers())
                if (!player.isAlive()) player.getPlayer().hidePlayer(PluginLoader.getInstance(), p);
            p.sendMessage(PluginLoader.getInstance().getManager().getGame().getMessages().get(Message.SPECTATOR, getNPlayer(p).getPreferences().getLang()));
            p.setGameMode(GameMode.SPECTATOR);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        GameManager manager = PluginLoader.getInstance().getManager();

        if (PluginLoader.getInstance().getManager().getState() != GameState.LOBBY) {
            manager.eliminatePlayer(getNPlayer(p));
            manager.unregisterPlayer(p.getName());
        }
    }

    @EventHandler
    public void onBreakBlock(BlockBreakEvent e) {
        if (PluginLoader.getInstance().getManager().getGameState().equals(GameState.LOBBY))
            e.setCancelled(true);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (PluginLoader.getInstance().getManager().getGameState().equals(GameState.LOBBY))
            e.setCancelled(true);
    }

    @EventHandler
    public void onKillPlayer(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            Player victim = (Player) e.getEntity();
            NPlayer nAttacker = new NPlayer(attacker.getName());
            NPlayer nVictim = new NPlayer(victim.getName());

            if (e.getDamager().getType() == EntityType.PLAYER) {
                if (victim.getHealth() - e.getFinalDamage() <= 0) {
                    e.setCancelled(true);

                    if (PluginLoader.getInstance().getManager().getGame().getLives() != -1) {
                        attacker.sendMessage(PluginLoader.getInstance().getManager().getGame().getMessages().get(Message.KILL_PLAYER, nAttacker.getPreferences().getLang()).replace("%player%", victim.getName()));
                        victim.sendMessage(PluginLoader.getInstance().getManager().getGame().getMessages().get(Message.ELIMINATED_BY, nVictim.getPreferences().getLang()).replace("%player%", attacker.getName()));
                        PluginLoader.getInstance().getManager().eliminatePlayer(nVictim);
                        victim.setGameMode(GameMode.SPECTATOR);

                        // Stats
                        nAttacker.getStatistics().setKills(nAttacker.getStatistics().getKills() + 1);
                        nVictim.getStatistics().setDeaths(nAttacker.getStatistics().getDeaths() + 1);
                    }
                }
            }
        }

    }
}
