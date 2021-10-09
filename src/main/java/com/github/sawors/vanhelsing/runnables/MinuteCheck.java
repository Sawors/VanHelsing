package com.github.sawors.vanhelsing.runnables;

import com.github.sawors.vanhelsing.tools.UsefulThings;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class MinuteCheck extends BukkitRunnable {

    private final JavaPlugin plugin;

    public MinuteCheck(JavaPlugin plugin){
        this.plugin = plugin;
    }


    @Override
    public void run() {
        //plugin.getServer().broadcast(Component.text(ChatColor.YELLOW + "heya bitches !"));


        for(World world : Bukkit.getWorlds()){
            if(world.getTime() >= 13000 && world.getTime() <= 13020){
                for(Player player : world.getPlayers()){

                    player.playSound(player.getLocation(), "minecraft:sawors.nightbells", .25f, 0.6f);
                    player.showTitle(Title.title(Component.text(ChatColor.DARK_PURPLE + "" + ChatColor.BOLD + "Night Has Fallen"),Component.text(ChatColor.DARK_GRAY + "end of day " + UsefulThings.getWorldDay(world)),Title.DEFAULT_TIMES));




                }
            } else if(world.getTime() >= 0 && world.getTime() <= 20){
                for(Player player : world.getPlayers()){

                    player.playSound(player.getLocation(), "minecraft:sawors.rooster", .25f, 1);
                    player.showTitle(Title.title(Component.text(ChatColor.YELLOW + "" + ChatColor.BOLD + "Sun Is Rising"),Component.text(ChatColor.DARK_GRAY + "beginning of day " + UsefulThings.getWorldDay(world)),Title.DEFAULT_TIMES));



                }
            }

        }

    }
}
