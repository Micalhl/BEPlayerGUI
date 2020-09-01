package me.mical.beplayergui.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.InventoryHolder;
import org.serverct.parrot.parrotx.data.inventory.InventoryExecutor;

import java.util.Objects;

public class InventoryListener implements Listener {

    @EventHandler
    public void onPlayerClick(InventoryClickEvent event) {
        InventoryHolder inventoryHolder = event.getInventory().getHolder();
        if (Objects.nonNull(inventoryHolder)) {
            if (inventoryHolder instanceof InventoryExecutor) {
                event.setCancelled(true);
                ((InventoryExecutor) inventoryHolder).execute(event);
            }
        }
    }

    @EventHandler
    public void onPlayerMoveItem(InventoryMoveItemEvent event) {
        InventoryHolder inventoryHolder = event.getDestination().getHolder();
        if (Objects.nonNull(inventoryHolder)) {
            if (inventoryHolder instanceof InventoryExecutor) {
                event.setCancelled(true);
            }
        }
    }
}
