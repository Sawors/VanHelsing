package com.github.sawors.vanhelsing;

import org.bukkit.NamespacedKey;

public class DataHolder {
    static NamespacedKey rolekey = new NamespacedKey(VanHelsing.getPlugin(VanHelsing.class), "role");
    static NamespacedKey immovablekey = new NamespacedKey((VanHelsing.getPlugin(VanHelsing.class)), "immovable");

    public static NamespacedKey getRoleKey(){
        return rolekey;
    }

    public static NamespacedKey getImmovablekey(){
        return immovablekey;
    }
}
