package me.mical.beplayergui;

import me.mical.beplayergui.command.CommandHandler;
import me.mical.beplayergui.config.ConfigManager;
import me.mical.beplayergui.config.DataManager;
import me.mical.beplayergui.listener.InventoryListener;
import me.mical.beplayergui.listener.PlayerListener;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.hooks.VaultUtil;

public final class BEPlayerGUI extends PPlugin {

    private static VaultUtil vaultUtil;

    public static VaultUtil getVaultUtil() {
        return vaultUtil;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        super.onEnable();
    }

    @Override
    protected void preload() {
        this.pConfig = new ConfigManager();
    }

    @Override
    protected void load() {
        DataManager.getInstance().init();
        registerCommand(new CommandHandler());
        vaultUtil = new VaultUtil(this, true);
        listen(pluginManager -> {
            pluginManager.registerEvents(new PlayerListener(), this);
            pluginManager.registerEvents(new InventoryListener(), this);
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
