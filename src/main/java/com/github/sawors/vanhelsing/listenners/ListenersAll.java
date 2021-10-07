package com.github.sawors.vanhelsing.listenners;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.Objects;

public class ListenersAll implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().hasItemMeta()){
            if(player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("testsword")){
                ItemStack item = player.getInventory().getItemInMainHand();
                if(item instanceof Damageable){
                    ((Damageable) item).setDamage((int) ((Math.random() * 249)+1));
                    player.sendMessage("ye");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event){
        Player player = event.getPlayer();
        if(player.getInventory().getItemInMainHand().hasItemMeta()){
            player.sendMessage("1");
            if(player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName().equals("testsword")){
                player.sendMessage("2");
                Damageable item = (Damageable) player.getInventory().getItemInMainHand();
                item.setDamage((int) ((Math.random() * 249)+1));
            }
        }
        if(event.getRightClicked() instanceof Player){
            Player rightclicked = (Player) event.getRightClicked();



            if(rightclicked.getEquipment().getItemInMainHand().hasItemMeta() && player.getInventory().getItemInMainHand().hasItemMeta()){
                String rightclickeditem = rightclicked.getEquipment().getItemInMainHand().getItemMeta().getLocalizedName();
                String playeritem = player.getInventory().getItemInMainHand().getItemMeta().getLocalizedName();
                switch(playeritem){
                    case "handcuffs":
                       player.sendMessage("heya");
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
