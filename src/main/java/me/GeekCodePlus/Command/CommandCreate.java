package me.GeekCodePlus.Command;

import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandCreate {


     public static void execute(CommandSender sender, Command command, String label, String[] args) {
         if (!sender.hasPermission("geekc.admin")) {
             sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
             GeekCodeMain.say("&7玩家缺少权限 §cgeekc.admin");
             return;
         }
         if (args.length >= 4 && args[0].equalsIgnoreCase("create") && ConfigManage.USER_ACTIVATION_CODE) {
             Player p = ((Player) sender).getPlayer();
             GeekCodeMain.activationActionManage.SaveCode(p, args[1], Integer.parseInt(args[2]), args[3]);
             return;
         }
         if (ConfigManage.USER_ACTIVATION_CODE) {
             sender.sendMessage("");
             sender.sendMessage("§b§lGeekCodePlus §8- §7§l帮助导航");
             sender.sendMessage("§B§l/geeki §3create §f[§7COMMAND§f] §f[§7amount§f] §f[§7perm§f] §8- §7生成激活码");
             sender.sendMessage("    §7参数 §fCOMMAND §7内空格使用 §F§l+ §7代替");
             sender.sendMessage("    §7玩家名使用 §F§l[player_name]§8/§F§l[player_uuid] §7代替");
             sender.sendMessage("    §7该激活码的使用权限，你可以自定义！");
             sender.sendMessage("");
             return;
         }
         GeekCodeMain.say("&c异常的使用，使用已经禁用的模块-§2激活码");
     }
}
