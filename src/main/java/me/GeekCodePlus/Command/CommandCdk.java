package me.GeekCodePlus.Command;


import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Module.ShareCode.GuiClick;
import me.GeekCodePlus.Module.ShareCode.Share_DataManage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCdk {


    public static void execute(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            GeekCodeMain.say(ChatColor.RED+"必须以玩家身份执行指令");
            return;
        }

        Player player = (Player) sender;
        String player_name = player.getName();
        if (!sender.hasPermission("geekc.command.cdk")) {
            sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
            GeekCodeMain.say("&7玩家缺少权限 §cgeekc.command.cdk");
            return;
        }

        if (args.length >= 2 && args[0].equalsIgnoreCase("cdk")) {
            boolean ACode = args[1].contains("GeekA");
            boolean ICode = args[1].contains("GeekI");
            boolean SCode = args[1].contains("GeekS");

            if (ACode && ConfigManage.USER_ACTIVATION_CODE) {
                GeekCodeMain.activationActionManage.UserCode(player, player_name, args[1]);
                return;
            }

            if (ICode && ConfigManage.USER_INVITE_CODE) {
                GeekCodeMain.inviteActionManage.UserCode(player, player_name, args[1]);
                return;
            }

            if (SCode && ConfigManage.USER_SHARE_CODE) {
                Share_DataManage.ShareObj data = Share_DataManage.getCodeMap.get(args[1]);
                if (data.getUSE_STATUS().equalsIgnoreCase("已使用")) {
                    for (String out : LangManage.CODE_GET) {
                        player.sendMessage(out);
                    }
                } else {
                    GuiClick.open(player, args[1]);
                }
                return;
            }
            for (String list_to_string : LangManage.USER_CDK_HELP) {
                sender.sendMessage(list_to_string);
            }
        }
    }

}
