package me.GeekCodePlus.Command;

import com.google.common.base.Joiner;
import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Module.DataManage.Share_DataManage;
import me.GeekCodePlus.utils.StreamSerializer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;


public class CommandShare {
    public static void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            GeekCodeMain.say(ChatColor.RED + "必须以玩家身份执行指令");
            return;
        }
        if (!sender.hasPermission("geekc.command.share")) {
            sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
            GeekCodeMain.say("§c geekc.command.share");
            return;
        }
        if (args.length == 2 && ConfigManage.USER_SHARE_CODE) {
            if (args[1].equalsIgnoreCase("put")) {
                Player player = (Player) sender;
                if (!GeekCodeMain.shareActionManage.isItem(player)) {
                    for (String out : LangManage.IS_MAIN) {
                        player.sendMessage(out);
                    }
                    return;
                }
                String name = GeekCodeMain.shareActionManage.getItemInHandName(player);
                String data = StreamSerializer.serializeInventory(GeekCodeMain.shareActionManage.getItemInHand(player));
                String code = GeekCodeMain.randomCode.getRandomGeekR();
                //储存至数据库
                Share_DataManage.insert("未开放", data, name, getItemAmount(player), code, player.getName(), "未使用");
                //获取最新的分享码本地储存
                Share_DataManage.getShareMeData(player.getName());
                outSmg(player, code);
            }
            if (args[1].equalsIgnoreCase("list")) {
                Player player = (Player) sender;
                String name = player.getName();
                //如果本地为空，前往数据库获取
                if (Share_DataManage.getMeCodeMap.isEmpty()) {
                    Share_DataManage.getShareMeData(name);
                    for (String out : LangManage.ME_CODE_UPDATE) {
                        player.sendMessage(out);
                    }
                    return;
                }
                //检查本地没有数据输出消息
                if (Share_DataManage.getMeCodeMap.get(name) == null) {
                    for (String out : LangManage.ME_CODE_NULL) {
                        player.sendMessage(out);
                    }
                    return;
                }
                String[] a = Joiner.on(",").join(Share_DataManage.getMeCodeMap.get(name)).split(",");
                GeekCodeMain.say(Arrays.toString(a));
                outAllSmg(a, player);
            }
        }
    }

    private static int getItemAmount(Player player) {
        try {
            return player.getInventory().getItemInMainHand().getAmount();
        } catch (NoSuchMethodError e) {
            return player.getInventory().getItemInHand().getAmount();
        }
    }

    private static void outSmg(Player player, String code) {
        TextComponent text = new TextComponent("    §7[§A点击复制§7]");
        text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, code));
        for (String out : LangManage.CODE_PUT) {
            player.sendMessage(out);
        }
        player.spigot().sendMessage(text);
        player.sendMessage(" \n ");
    }

    private static void outAllSmg(String[] s, Player player) {
        for (String out : LangManage.ME_CODE_SHARE) {
            player.sendMessage(out);
        }
        for (String a : s) {
            TextComponent text = new TextComponent("    " + a + "    §7[§a打开提货界面§7]");
            TextComponent text2 = new TextComponent("    §7[§A复制分享码§7]");
            text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gkc cdk " + a));
            text2.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, a));
            player.sendMessage("");
            player.spigot().sendMessage(text);
            player.spigot().sendMessage(text2);
        }
        player.sendMessage(" \n ");
    }
}