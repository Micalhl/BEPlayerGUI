package me.mical.beplayergui.data;

import me.mical.beplayergui.config.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.data.PData;
import org.serverct.parrot.parrotx.data.PID;
import org.serverct.parrot.parrotx.data.flags.Timestamp;
import org.serverct.parrot.parrotx.utils.I18n;

import java.io.File;
import java.io.IOException;

public class PlayerDataManager extends PData implements Timestamp {

    private String uuid;
    private long joinTime;

    public PlayerDataManager(File file, PID id) {
        super(file, id);
        load(file);
    }

    public PlayerDataManager(String uuid, long joinTime) {
        super(new File(DataManager.getInstance().getFile(), uuid + ".yml"),
                DataManager.getInstance().buildId(uuid));
        this.uuid = uuid;
        this.joinTime = joinTime;
        save();
    }

    public PlayerDataManager(String uuid) {
        super(new File(DataManager.getInstance().getFile(), uuid + ".yml"),
                DataManager.getInstance().buildId(uuid));
        this.uuid = uuid;
        load(file);
    }

    @Override
    public String getTypeName() {
        return "玩家" + uuid + "的数据/" + getFileName();
    }

    @Override
    public void init() {
    }

    @Override
    public void saveDefault() {
    }

    @Override
    public void load(File file) {
        try {
            uuid = getFileName();
            uuid = data.getString("Player");
            joinTime = data.getLong("JoinTime");
        } catch (Throwable e) {
            plugin.lang.logError(I18n.LOAD, getTypeName(), e, null);
        }
    }

    @Override
    public void save() {
        if (!file.exists()) {
            data.set("Player", uuid);
            data.set("JoinTime", joinTime);
            try {
                data.save(file);
            } catch (IOException e) {
                plugin.lang.logError(I18n.SAVE, getTypeName(), e, null);
            }
        }
    }

    @Override
    public long getTimestamp() {
        return joinTime;
    }

    @Override
    public void setTime(long time) {
        joinTime = time;
    }
}
