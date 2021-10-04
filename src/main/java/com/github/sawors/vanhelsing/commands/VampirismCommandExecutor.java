package com.github.sawors.vanhelsing.commands;

import com.github.sawors.vanhelsing.DataHolder;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class VampirismCommandExecutor implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args ){
        if(sender instanceof Player){
            Player p = ((Player) sender).getPlayer();
            if(p != null){
                if(Objects.equals(p.getPersistentDataContainer().get(DataHolder.getRoleKey(), PersistentDataType.STRING), "vampire")){
                    p.getPersistentDataContainer().set(DataHolder.getRoleKey(), PersistentDataType.STRING, "villager");
                } else {
                    p.getPersistentDataContainer().set(DataHolder.getRoleKey(), PersistentDataType.STRING, "vampire");
                }
                p.sendMessage(ChatColor.DARK_PURPLE + "Your new role is : " + ChatColor.RED + p.getPersistentDataContainer().get(DataHolder.getRoleKey(), PersistentDataType.STRING));
                return true;
            }

        }
        return false;
    }
}
