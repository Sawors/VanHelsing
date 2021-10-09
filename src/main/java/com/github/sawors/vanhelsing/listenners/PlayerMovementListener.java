package com.github.sawors.vanhelsing.listenners;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import com.github.sawors.vanhelsing.tools.UsefulThings;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMovementListener implements Listener {

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event){
    }

    @EventHandler
    public void onPlayerToggleSprint(PlayerJumpEvent event){
        Player p = event.getPlayer();

        if(UsefulThings.isPlayerCrawling(p)){
            event.getPlayer().setWalkSpeed(0.4f);
        } else {
            event.getPlayer().setWalkSpeed(0.2f);
        }
    }
}
