package com.nostalgia.modes.uhcrun.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class GameModeEvent implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        boolean isGoldenApple = new Random().nextInt(100) < 8;

        //Ore drops modifiers
        if (b.getType().toString().contains("IRON_ORE")) {
            int amount = new Random().nextInt(4) + 1;
            e.setDropItems(false);
            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.IRON_INGOT, amount));
        } else if (b.getType().toString().contains("GOLD_ORE")) {
            int amount = new Random().nextInt(4) + 1;
            e.setDropItems(false);
            if (isGoldenApple)
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLDEN_APPLE, 1));
            b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLD_INGOT, amount));
        }

        //Tree chopper
        if (b.getType().toString().contains("LOG")) {
            int goldenAppleAmount = new Random().nextInt(2) + 1;
            int x = b.getLocation().getBlockX();
            int y = b.getLocation().getBlockY();
            int z = b.getLocation().getBlockZ();

            for (int i = x - 1; i < x + 1; i++) {
                for (int j = y - 1; j < y + 1; j++) {
                    for (int k = z - 1; k < z + 1; k++) {
                        Block block = b.getWorld().getBlockAt(i, j, k);
                        if (block.getType().toString().contains("LOG") || block.getType().toString().contains("LEAVES"))
                            block.breakNaturally();
                    }
                }
            }

            if (isGoldenApple)
                b.getWorld().dropItemNaturally(b.getLocation(), new ItemStack(Material.GOLDEN_APPLE, goldenAppleAmount));
        }
    }

}
