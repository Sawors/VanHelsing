package com.github.sawors.vanhelsing.listenners;

import com.github.sawors.vanhelsing.DataHolder;
import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Objects;

public class ForbiddenMoveItemListener implements Listener {

    @EventHandler
    public void onPlayerClickEvent(InventoryClickEvent event){

        ItemStack item = null;
        if(event.getCurrentItem() != null) {item = event.getCurrentItem();}
        HumanEntity p = event.getWhoClicked();

            if(item != null && item.hasItemMeta() && Objects.equals(item.getItemMeta().getPersistentDataContainer().get(DataHolder.getImmovablekey(), PersistentDataType.INTEGER),1 )){
                p.sendActionBar(Component.text(ChatColor.RED + "you cannot do that while handcuffed !"));
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1f, 0.1f);
                event.setCancelled(true);
            }

    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event){
        ItemStack mhitem = event.getItemDrop().getItemStack();
        if(mhitem.hasItemMeta() && Objects.equals(mhitem.getItemMeta().getPersistentDataContainer().get(DataHolder.getImmovablekey(), PersistentDataType.INTEGER), 1) ){
            event.getPlayer().sendActionBar(Component.text(ChatColor.RED + "you cannot do that while handcuffed !"));
            event.getPlayer().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1f, 0.1f);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClickAtEntity(PlayerInteractEntityEvent event){
        Player p = event.getPlayer();
        ItemStack item = p.getActiveItem();
        if(item != null && item.hasItemMeta() && Objects.equals(item.getItemMeta().getPersistentDataContainer().get(DataHolder.getImmovablekey(), PersistentDataType.INTEGER),1 )){
            p.sendActionBar(Component.text(ChatColor.RED + "you cannot do that while handcuffed !"));
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1f, 0.1f);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteractAtVehicle(VehicleEnterEvent event){
        if(event.getEntered() instanceof Player && !(event.getVehicle() instanceof Minecart || event.getVehicle() instanceof ArmorStand || event.getVehicle() instanceof Boat)){
            ItemStack item = ((Player) event.getEntered()).getInventory().getItemInMainHand();
            if(item.hasItemMeta() && Objects.equals(item.getItemMeta().getPersistentDataContainer().get(DataHolder.getImmovablekey(), PersistentDataType.INTEGER),1 )){
                //event.getEntered().teleport(event.getEntered().getLocation().add(0,0.25,0));
                event.getEntered().sendActionBar(Component.text(ChatColor.RED + "you cannot do that while handcuffed !"));
                event.getEntered().getWorld().playSound(event.getEntered().getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1f, 0.1f);
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPlayerInteractAtBlock(PlayerInteractEvent event){
        Player p = event.getPlayer();
        ItemStack item = p.getInventory().getItemInMainHand();
        if(item.hasItemMeta() && Objects.equals(item.getItemMeta().getPersistentDataContainer().get(DataHolder.getImmovablekey(), PersistentDataType.INTEGER),1 )){
            //event.getEntered().teleport(event.getEntered().getLocation().add(0,0.25,0));
            p.sendActionBar(Component.text(ChatColor.RED + "you cannot do that while handcuffed !"));
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1f, 0.1f);
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerSwitchItemInHand(PlayerItemHeldEvent event){
        Player p = event.getPlayer();
        ItemStack item = p.getInventory().getItem(event.getPreviousSlot());
        if(item != null && item.hasItemMeta() && Objects.equals(item.getItemMeta().getPersistentDataContainer().get(DataHolder.getImmovablekey(), PersistentDataType.INTEGER),1 )){
            //event.getEntered().teleport(event.getEntered().getLocation().add(0,0.25,0));
            p.sendActionBar(Component.text(ChatColor.RED + "you cannot do that while handcuffed !"));
            p.getWorld().playSound(p.getLocation(), Sound.BLOCK_NETHERITE_BLOCK_BREAK, 1f, 0.1f);
            event.setCancelled(true);
        }

    }


}

