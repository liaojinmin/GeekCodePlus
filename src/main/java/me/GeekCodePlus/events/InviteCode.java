package me.GeekCodePlus.events;

import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.utils.PlaceholderAPI.papiDataHead;
import me.GeekCodePlus.utils.PlaceholderAPI.papiOwnerObj;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class InviteCode implements Listener {

    @EventHandler
    public void onMsgOwner(PlayerJoinEvent event) {
        if (papiDataHead.getOwnerData.isEmpty()) {
            return;
        }
        Player player = event.getPlayer();
        papiOwnerObj res = papiDataHead.getOwnerData.get(player.getName());
        if (res == null) {
            return;
        }
        if (res.O_REWARD >= 1) {
            String txt = LangManage.PLUGIN_NAME + " §a你有新的邀请码奖励待领取！";
            TextComponent text = new TextComponent(txt + "§7[§a§l一键领取§7]");
            text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gkc inv get"));
            player.sendMessage(text);
        }
    }


}
