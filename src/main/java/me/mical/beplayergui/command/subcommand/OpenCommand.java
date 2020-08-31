package me.mical.beplayergui.command.subcommand;

import me.mical.beplayergui.BEPlayerGUI;
import me.mical.beplayergui.data.PlayerInventory;
import org.serverct.parrot.parrotx.command.BaseCommand;
import org.serverct.parrot.parrotx.utils.BasicUtil;

public class OpenCommand extends BaseCommand {
    public OpenCommand() {
        super(BEPlayerGUI.getInstance(),
                "open",
                0);
        describe("打开玩家界面");
        perm(null);
    }

    @Override
    protected void call(String[] args) {
        BasicUtil.openInventory(plugin, user, new PlayerInventory(user).getInventory());
    }
}
