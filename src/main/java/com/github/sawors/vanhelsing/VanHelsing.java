package com.github.sawors.vanhelsing;

import com.github.sawors.vanhelsing.commands.VampirismCommandExecutor;
import com.github.sawors.vanhelsing.commands.VgiveCommandExecutor;
import com.github.sawors.vanhelsing.listenners.ForbiddenMoveItemListener;
import com.github.sawors.vanhelsing.listenners.ListenersAll;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class VanHelsing extends JavaPlugin {

    @Override
    public void onEnable() {


        getServer().getPluginManager().registerEvents(new ListenersAll(), this);
        getServer().getPluginManager().registerEvents(new ForbiddenMoveItemListener(), this);

        // Plugin startup logic
        Bukkit.getLogger().log(Level.WARNING, ChatColor.DARK_PURPLE +"Blood will flow...");
        getServer().getPluginCommand("vampirism").setExecutor(new VampirismCommandExecutor());
        getServer().getPluginCommand("vgive").setExecutor(new VgiveCommandExecutor());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }



}
