package me.mical.beplayergui.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BasicUtil {

    public static List<Player> playerList = new ArrayList<>(getOnlinePlayers());

    public static Collection<? extends Player> getOnlinePlayers() {
        return Bukkit.getOnlinePlayers();
    }

    public static int getOnlinePlayerSize() {
        return getOnlinePlayers().size();
    }
}
