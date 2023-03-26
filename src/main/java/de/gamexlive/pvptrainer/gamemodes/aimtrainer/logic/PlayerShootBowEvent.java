package de.gamexlive.pvptrainer.gamemodes.aimtrainer.logic;

import de.gamexlive.pvptrainer.PvPTrainer;
import de.gamexlive.pvptrainer.stats.PlayerStats;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

public class PlayerShootBowEvent implements Listener {

    @EventHandler
    public void onPlayerShootBow(EntityShootBowEvent event) {
        event.setCancelled(false);
        if(event.getProjectile() instanceof Arrow) {
            if (event.getEntity() instanceof Player) {
                Player p = (Player) event.getEntity();
                if(PvPTrainer.manager.getPlayer(p) != null){
                    PlayerStats stats = PvPTrainer.manager.getPlayer(p);
                    stats.addShot();
                    stats.updateAccuracy();
                }
            }
        }

    }

}
