package me.mical.beplayergui.data.prompt;

import me.mical.beplayergui.BEPlayerGUI;
import org.bukkit.Location;
import org.bukkit.conversations.ConversationContext;
import org.bukkit.conversations.Prompt;
import org.bukkit.conversations.StringPrompt;
import org.bukkit.entity.Player;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.utils.I18n;

import java.util.Objects;

public class ConfirmTelerportPrompt extends StringPrompt {

    private final PPlugin plugin;
    private final Player user;
    private final Player target;

    public ConfirmTelerportPrompt(Player user, Player target) {
        this.plugin = BEPlayerGUI.getInstance();
        this.user = user;
        this.target = target;
    }

    @Override
    public String getPromptText(ConversationContext context) {
        return plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "是否要传送到玩家 &c" + target.getName() + " &7身边? (&cy&7/&cn&7)");
    }

    @Override
    public Prompt acceptInput(ConversationContext context, String input) {
        if (Objects.equals(input, "y")) {
            Location location = target.getLocation();
            user.teleport(location);
            String successfulMsg = plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "你已传送至玩家 &c" + target.getName() + " &7身边...");
            String receiveMsg = plugin.lang.build(plugin.localeKey, I18n.Type.WARN, "玩家 &c" + user.getName() + " &7已传送到你身边...");
            I18n.sendAsync(plugin, user, successfulMsg);
            I18n.sendAsync(plugin, target, receiveMsg);
        } else {
            String cancelMsg = plugin.lang.build(plugin.localeKey, I18n.Type.WARN, "操作已取消.");
            I18n.sendAsync(plugin, user, cancelMsg);
        }
        return Prompt.END_OF_CONVERSATION;
    }
}
