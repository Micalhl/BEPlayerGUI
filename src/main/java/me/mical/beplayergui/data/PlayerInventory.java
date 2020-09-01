package me.mical.beplayergui.data;

import com.destroystokyo.paper.profile.PlayerProfile;
import me.mical.beplayergui.BEPlayerGUI;
import me.mical.beplayergui.config.ConfigManager;
import me.mical.beplayergui.data.prompt.ConfirmTelerportPrompt;
import me.mical.beplayergui.utils.BasicUtil;
import me.soul.tradesystem.api.TradeSystemAPI;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.serverct.parrot.parrotx.PPlugin;
import org.serverct.parrot.parrotx.data.inventory.InventoryExecutor;
import org.serverct.parrot.parrotx.utils.ConversationUtil;
import org.serverct.parrot.parrotx.utils.I18n;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerInventory implements InventoryExecutor {

    protected PPlugin plugin;
    protected Player user;
    protected Inventory inventory;

    public PlayerInventory(Player user) {
        this.plugin = BEPlayerGUI.getInstance();
        this.user = user;
        this.inventory = construct();
    }

    @Override
    public Inventory construct() {
        Inventory inventory1 = Bukkit.createInventory(this, 6 * 9, ConfigManager.inventoryTitle);
        for (Player p : BasicUtil.getOnlinePlayers()) {
                ItemStack itemStack = new ItemStack(Material.PLAYER_HEAD, 1);
                ItemMeta meta = itemStack.getItemMeta();
                meta.setDisplayName(p.getName());
                String[] loreFormat = {
                        "",
                        "§7玩家信息 ▶",
                        "  §7玩家名: §c" + p.getName(),
                        "  §7UUID: §c" + p.getUniqueId().toString(),
                        "  §7加入时间: §c" + new PlayerDataManager(p.getUniqueId().toString()).getTime(),
                        "  §7持有金币: §c" + BEPlayerGUI.getVaultUtil().getBalances(p),
                        "",
                        "  §a▶ §f左键来传送到该玩家",
                        "  §b▶ §f右键和玩家进行交易",
                        ""
                };
                meta.setLore(new ArrayList<>(Arrays.asList(loreFormat)));
                SkullMeta skullMeta = (SkullMeta) meta;
                PlayerProfile playerProfile = p.getPlayerProfile();
                skullMeta.setPlayerProfile(playerProfile);
                itemStack.setItemMeta(skullMeta);
                inventory1.addItem(itemStack);
        }
        return inventory1;
    }

    @Override
    public void execute(InventoryClickEvent event) {
        Player user = (Player) event.getWhoClicked();
        ClickType clickType = event.getClick();
        ItemStack itemStack = event.getCurrentItem();
        if (itemStack != null && itemStack.getType() != Material.AIR) {
            Player player1 = Bukkit.getPlayer(itemStack.getItemMeta().getDisplayName());
            if (clickType == ClickType.LEFT) {
                ConversationUtil.start(BEPlayerGUI.getInstance(),
                        user,
                        new ConfirmTelerportPrompt(user, player1), 120);
            } else if (clickType == ClickType.RIGHT) {
                user.closeInventory();
                TradeSystemAPI tradeSystemAPI = new TradeSystemAPI();
                try {
                    tradeSystemAPI.sendTradeRequest(user, player1);
                    //String sendMsg = plugin.lang.build(plugin.localeKey, I18n.Type.INFO, "你已经成功向 &c" + player1.getName() + " &7发送了交易请求.");
                    String sendMsg = plugin.lang.getWithFormat(plugin.localeKey, I18n.Type.INFO, "Lang", "Inventory.SendMsg", player1.getName());
                    I18n.send(user, sendMsg);
                } catch (Exception exception) {
                   // String failMsg = plugin.lang.build(plugin.localeKey, I18n.Type.ERROR, "由于未知错误, 无法发送交易请求.");
                    String failMsg = plugin.lang.get(plugin.localeKey, I18n.Type.ERROR, "Lang", "Inventory.FailMsg");
                    I18n.send(user, failMsg);
                    plugin.lang.logError(I18n.EXECUTE, user.getName() + " 和 " + player1.getName() + " 的交易", exception, null);
                }
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
