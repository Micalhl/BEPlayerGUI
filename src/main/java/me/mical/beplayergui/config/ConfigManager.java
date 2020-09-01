package me.mical.beplayergui.config;

import me.mical.beplayergui.BEPlayerGUI;
import org.serverct.parrot.parrotx.config.PConfig;
import org.serverct.parrot.parrotx.utils.I18n;

import java.io.File;
import java.util.Objects;

public class ConfigManager extends PConfig {

    public static String inventoryTitle;

    public ConfigManager() {
        super(BEPlayerGUI.getInstance(),
                "config",
                "主配置文件");
    }

    @Override
    public void load(File file) {
        inventoryTitle = I18n.color(Objects.requireNonNull(getConfig().getString("InventoryTitle")));
    }
}
