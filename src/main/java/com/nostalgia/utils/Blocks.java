package com.nostalgia.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Blocks {

    public static void generateChestLoot(Location loc, HashMap<Material, Integer> lootChances) {
        Block block = loc.getBlock();
        if (block.getType() != Material.CHEST) {
            return;
        }

        Chest chest = (Chest) block.getState();
        Inventory inv = chest.getBlockInventory();

        for (int i = 0; i < inv.getSize(); i++) {
            int random = new Random().nextInt(100);
            int chance = 0;
            for (Map.Entry<Material, Integer> entry : lootChances.entrySet()) {
                chance += entry.getValue();
                if (random <= chance) {
                    inv.setItem(i, new ItemStack(entry.getKey()));
                    break;
                }
            }
        }
    }
}
