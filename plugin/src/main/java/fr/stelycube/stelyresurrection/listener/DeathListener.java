package fr.stelycube.stelyresurrection.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.jetbrains.annotations.Nullable;

public class DeathListener implements Listener {

    private final String deathMessage;

    public DeathListener(@Nullable String deathMessage) {
        this.deathMessage = deathMessage;
    }

    @SuppressWarnings("unused")
    @EventHandler(priority = EventPriority.HIGH)
    private void onDeath(PlayerDeathEvent e) {
        final Player deadPlayer = e.getEntity();
        if (deadPlayer.hasPermission("res.keepInventory")) {
            e.setKeepInventory(true);
            e.getDrops().clear();
        }

        if (deadPlayer.hasPermission("res.keepXp")) {
            e.setKeepLevel(true);
            e.setDroppedExp(0);
        }

        if (deathMessage != null) {
            deadPlayer.sendMessage(deathMessage);
        }
    }

}
