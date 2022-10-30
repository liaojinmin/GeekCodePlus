package me.GeekCodePlus.Command;

import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandInvite {


    public static void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            GeekCodeMain.say(ChatColor.RED+"必须以玩家身份执行指令");
            return;
        }
        if (!ConfigManage.USER_INVITE_CODE) return;
        Player p = (Player) sender;
        String uuid = String.valueOf(((Player) sender).getUniqueId());
        String name = sender.getName();
        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("buy")) {
                if (!sender.hasPermission("geekc.command.inv.buy")) {
                    sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
                    GeekCodeMain.say("§c geekc.command.inv.buy");
                    return;
                }
                if (GeekCodeMain.econ.has(p, ConfigManage.BUY_CDK_MONEY)) {
                    GeekCodeMain.inviteActionManage.BuyCdk(p, name, uuid);
                    return;
                }
                for (String out : LangManage.NOT_MONEY) {
                    p.sendMessage(out);
                }
                return;
            }
        }

        if (args.length >= 2) {
            if (args[1].equalsIgnoreCase("get")) {
                if (!sender.hasPermission("geekc.command.inv.get")) {
                    sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
                    GeekCodeMain.say("§c geekc.command.inv.get");
                    return;
                }
                int r = GeekCodeMain.inviteActionManage.getOwner_Reward(name);
                if (r >= 1) {
                    GeekCodeMain.inviteActionManage.getRewardCheck(p);
                } else if (r == 0) {
                    for (String out : LangManage.NOT_REWARD) {
                        sender.sendMessage(out);
                    }
                }
                return;
            }
        }
        for (String list_to_string : LangManage.INVITE_HELP) {
            sender.sendMessage(list_to_string);
        }
    }
}
