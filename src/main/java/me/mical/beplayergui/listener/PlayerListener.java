package me.mical.beplayergui.listener;

import me.mical.beplayergui.config.DataManager;
import me.mical.beplayergui.data.PlayerDataManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player user = event.getPlayer();
        String uuid = user.getUniqueId().toString();
        long timestamp = System.currentTimeMillis();
        DataManager.getInstance().put(new PlayerDataManager(uuid, timestamp));
    }
}
