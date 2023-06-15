package com.nostalgia.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Items {

    public static ItemStack create(String name, Material m, int amount, List<String> lore) {
        ItemStack is = new ItemStack(m, amount);
        ItemMeta im = is.getItemMeta();

        assert im != null;
        im.setDisplayName(name);
        im.setLore(lore);
        is.setItemMeta(im);
        return is;
    }
}
