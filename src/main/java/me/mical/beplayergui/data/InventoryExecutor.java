package me.mical.beplayergui.data;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public interface InventoryExecutor extends InventoryHolder {

    Inventory construct();

    void execute(InventoryClickEvent event);
}
