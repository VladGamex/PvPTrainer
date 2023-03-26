package de.gamexlive.pvptrainer.gamemodes.aimtrainer.logic;

import de.gamexlive.pvptrainer.PvPTrainer;
import de.gamexlive.pvptrainer.stats.PlayerStats;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityKillEvent implements Listener {

    @EventHandler
    public void onEntityKill(EntityDamageByEntityEvent event) {
        if(event.getEntity() instanceof Llama) {
            if(event.getDamager().getType() != EntityType.ARROW) { return; }
            Arrow arrow = (Arrow) event.getDamager();
            if(!(arrow.getShooter() instanceof Player)) { return; }
            Player shooter = (Player) arrow.getShooter();
            if(PvPTrainer.manager.getPlayer(shooter) != null) {
                PlayerStats stats = PvPTrainer.manager.getPlayer(shooter);
                stats.addKill();
                stats.updateAccuracy();
            }
        }

    }

}
