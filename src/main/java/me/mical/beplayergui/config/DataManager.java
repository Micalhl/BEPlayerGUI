package me.mical.beplayergui.config;

import me.mical.beplayergui.BEPlayerGUI;
import me.mical.beplayergui.data.PlayerDataManager;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.serverct.parrot.parrotx.config.PFolder;
import org.serverct.parrot.parrotx.utils.BasicUtil;

import javax.xml.crypto.Data;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataManager extends PFolder {
    private static DataManager instance;

    public DataManager() {
        super(BEPlayerGUI.getInstance(),
                "data",
                "数据文件夹",
                "DATA");
    }

    public static DataManager getInstance() {
        if (Objects.isNull(instance)) {
            instance = new DataManager();
        }
        return instance;
    }

    public List<PlayerDataManager> getAll() {
        List<PlayerDataManager> playerDataManagers = new ArrayList<>();
        dataMap.values().forEach(pData -> playerDataManagers.add((PlayerDataManager) pData));
        return playerDataManagers;
    }

    @Override
    public void load(File file) {
        put(new PlayerDataManager(file, buildId(BasicUtil.getNoExFileName(file.getName()))));
    }
}
