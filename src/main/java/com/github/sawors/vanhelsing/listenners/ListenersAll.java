package com.github.sawors.vanhelsing.listenners;

import com.github.sawors.vanhelsing.tools.UsefulThings;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleSwimEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class ListenersAll implements Listener {

    @EventHandler
    public void on(EntityToggleSwimEvent event){
        Bukkit.getServer().broadcast(Component.text("crawl"));

        }


    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        if(event.getRightClicked() instanceof Player){
            Player rightclicked = (Player) event.getRightClicked();



            if(rightclicked.getEquipment().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().hasItemMeta()){
                String rightclickeditem = rightclicked.getEquipment().getItemInMainHand().getItemMeta().getLocalizedName();
                String playeritem = player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();
                switch(playeritem){
                    case "handcuffs":
                       if(UsefulThings.isBehind(player, rightclicked, 45)){
                           UsefulThings.handcuffPlayer(rightclicked);
                           ItemStack itemtemp = player.getInventory().getItemInMainHand().clone();
                           itemtemp.setAmount(itemtemp.getAmount()-1);
                           player.getInventory().setItem(player.getInventory().getHeldItemSlot(), itemtemp);
                       }
                }
                if(Objects.equals(rightclickeditem, "beer") && Objects.equals(playeritem, "beer")){
                    player.getWorld().playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0.8f);
                    player.getWorld().spawnParticle(Particle.SPIT, player.getLocation().add(0,1,0), 8, .5, .5,.5 ,0 ,0);
                    rightclicked.getWorld().playSound(rightclicked.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0.8f);
                    rightclicked.getWorld().spawnParticle(Particle.SPIT, rightclicked.getLocation().add(0,1,0), 8, .5, .5,.5 ,0 ,0);

                }

            }
        }
    }




}
