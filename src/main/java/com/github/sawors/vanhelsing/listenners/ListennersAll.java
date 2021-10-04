package com.github.sawors.vanhelsing.listenners;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class ListennersAll {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        if(event.getRightClicked() instanceof Player){
            Player rightclicked = (Player) event.getRightClicked();
            Player player = event.getPlayer();

            if(rightclicked.getInventory().getItemInMainHand().hasItemMeta() && rightclicked.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("beer") && player.getInventory().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("beer") ){
                player.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0.8f);
                player.getWorld().spawnParticle(Particle.SPIT, player.getLocation().add(0,1,0), 8, .5, .5,.5 ,0 ,0);
                rightclicked.playSound(player.getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, 1, 0.8f);
                rightclicked.getWorld().spawnParticle(Particle.SPIT, rightclicked.getLocation().add(0,1,0), 8, .5, .5,.5 ,0 ,0);

            }
        }
    }
}
