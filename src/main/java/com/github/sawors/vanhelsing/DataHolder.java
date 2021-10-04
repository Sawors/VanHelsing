package com.github.sawors.vanhelsing;

import org.bukkit.NamespacedKey;

public class DataHolder {
    static NamespacedKey rolekey = new NamespacedKey(VanHelsing.getPlugin(VanHelsing.class), "role");

    public static NamespacedKey getRoleKey(){
        return rolekey;
    }
}
