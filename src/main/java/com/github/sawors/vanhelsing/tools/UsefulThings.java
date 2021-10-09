package com.github.sawors.vanhelsing.tools;

import net.kyori.adventure.text.Component;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;

public class UsefulThings {

    public static void handcuffPlayer(Player p){

        ItemStack item = new ItemStack(Material.IRON_NUGGET);
        ItemMeta meta = item.getItemMeta();
        ArrayList<Component> lore = new ArrayList<>();

        item.setType(Material.IRON_NUGGET);
        meta.displayName(Component.text(ChatColor.GRAY + "Handcuffs"));
        meta.setUnbreakable(true);
        meta.setLocalizedName("handcuffsON");
        lore.add(Component.text(""));
        lore.add(Component.text(ChatColor.RED + "You are now handcuffed, you are prevented from :"));
        lore.add(Component.text(ChatColor.DARK_RED + "- Changing your item in main hand"));
        lore.add(Component.text(ChatColor.DARK_RED + "- Interacting at blocks"));
        lore.add(Component.text(ChatColor.DARK_RED + "- Interacting with chest/furnace etc..."));
        lore.add(Component.text(ChatColor.DARK_RED + "- Interacting with horses/donkeys/mules"));
        lore.add(Component.text(ChatColor.DARK_RED + "- Dropping items"));
        meta.getPersistentDataContainer().set(DataHolder.getImmovablekey(), PersistentDataType.INTEGER, 1);
        meta.lore(lore);

        item.setItemMeta(meta);

        if (p.getInventory().getItemInMainHand().getType() != Material.AIR) {
            p.getWorld().dropItemNaturally(p.getLocation(), p.getInventory().getItemInMainHand());
        }

        if (p.getInventory().getItemInOffHand().getType() != Material.AIR) {
            p.getWorld().dropItemNaturally(p.getLocation(), p.getInventory().getItemInOffHand());
        }
        p.getWorld().playSound(p.getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 0.75f);
        p.getInventory().setItem(p.getInventory().getHeldItemSlot(), item);
        p.getInventory().setItem(EquipmentSlot.OFF_HAND, item);
    }

    public static void uncuffPlayer(Player p){
        if(p.getInventory().getItemInMainHand().hasItemMeta() && p.getInventory().getItemInOffHand().hasItemMeta()){
            ItemMeta metamain = p.getInventory().getItemInMainHand().getItemMeta();
            ItemMeta metaoff = p.getInventory().getItemInOffHand().getItemMeta();

            if(metamain.getLocalizedName().equals("handcuffsON") && metaoff.getLocalizedName().equals("handcuffsON")){
                p.getInventory().setItem(p.getInventory().getHeldItemSlot(), null);
                p.getInventory().setItem(EquipmentSlot.OFF_HAND, null);
                p.getWorld().playSound(p.getLocation(), Sound.BLOCK_IRON_TRAPDOOR_OPEN, 1f, 1.5f);
            }
        }


    }

    /**
     * Used to check if an entity is behind another, generally while attacking (mainly for backstabs)
     * the default angle for this method is +-22.5 degrees (considering the back of the entity to be 0 degree)
     * @param attacker the entity performing the attack (typically EntityDamageByEntityEvent.getDamager())
     * @param victim the entity which is being attacked (typically EntityDamageByEntityEvent.getEntity())
     * @return true if the attacker is behind the victim, false otherwise
     */
    public static boolean isBehind(Player attacker, LivingEntity victim){
        if(Math.abs(victim.getLocation().getYaw()) > 180){
            return Math.abs((Math.abs(victim.getLocation().getYaw()-360)) - Math.abs(attacker.getLocation().getYaw())) <= 22.5;
        } else{
            return Math.abs(Math.abs(victim.getLocation().getYaw()) - Math.abs(attacker.getLocation().getYaw())) <= 22.5;
        }
    }

    /**
     * Used to check if an entity is behind another, generally while attacking (mainly for backstabs)
     * the default angle for this method is +-22.5 degrees (considering the back of the entity to be 0 degree)
     * @param attacker the entity performing the attack (typically EntityDamageByEntityEvent.getDamager())
     * @param victim the entity which is being attacked (typically EntityDamageByEntityEvent.getEntity())
     * @param range used to specify the "width" of the cone behind the victim where this method returns true (example : 90 will return true when attacker is behind the entity at +-90 degrees)
     * @return true if the attacker is behind the victim, false otherwise
     */
    public static boolean isBehind(Player attacker, LivingEntity victim, double range){
        if(Math.abs(victim.getLocation().getYaw()) > 180){
            return Math.abs((Math.abs(victim.getLocation().getYaw()-360)) - Math.abs(attacker.getLocation().getYaw())) <= range;
        } else{
            return Math.abs(Math.abs(victim.getLocation().getYaw()) - Math.abs(attacker.getLocation().getYaw())) <= range;
        }
    }

    public static int getWorldDay(World w){
        return (int) Math.floor((double) (w.getFullTime()/24000));
    }

    public static boolean isPlayerCrawling(Player p){
        return p.getEyeLocation().getY() - p.getLocation().getY() <= 0.5 && !p.isGliding() && !p.isInWater() && !p.isRiptiding() && !p.isFlying() && !p.isSleeping();
    }

}
