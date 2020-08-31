package me.mical.beplayergui.config;

import me.mical.beplayergui.BEPlayerGUI;
import org.serverct.parrot.parrotx.config.PConfig;

public class ConfigManager extends PConfig {
    public ConfigManager() {
        super(BEPlayerGUI.getInstance(),
                "config",
                "主配置文件");
    }
}
