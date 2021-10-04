package com.github.sawors.vanhelsing.commands;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class VgiveCommandExecutor implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        if(args != null && args.length >= 1 && sender instanceof Player && ((Player) sender).getPlayer() != null){
            Player p = ((Player) sender).getPlayer();
            ItemStack item = new ItemStack(Material.AIR);
            ItemMeta meta = item.getItemMeta();
            switch(args[0]){
                case "beer":
                    item.setType(Material.POTION);
                    PotionMeta pmeta = (PotionMeta) item.getItemMeta();
                    pmeta.setLocalizedName("beer");
                    pmeta.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 30*20,1), true);
                    pmeta.addCustomEffect(new PotionEffect(PotionEffectType.CONFUSION, 8*20,1), true);
                    pmeta.addCustomEffect(new PotionEffect(PotionEffectType.WEAKNESS, 30*20,0), true);
                    pmeta.addCustomEffect(new PotionEffect(PotionEffectType.HEAL, 1,0), true);
                    pmeta.setColor(Color.YELLOW);
                    pmeta.addItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
                    pmeta.displayName(Component.text(ChatColor.GOLD + "Beer Mug"));
                    if(pmeta.hasLore()){
                        pmeta.lore().add(Component.text(""));
                        pmeta.lore().add(Component.text(ChatColor.GOLD + "Hold my beer !"));
                    }

                    item.setItemMeta(pmeta);
                    break;


            }
            if(args.length >= 2 && args[1] != null && Integer.parseInt(args[1]) >= 1){
                for(int i = 0; i<Integer.parseInt(args[1]); i++){
                    p.getInventory().addItem(item);
                }
            }else{
                p.getInventory().addItem(item);
            }

        }
        return false;
    }
}
