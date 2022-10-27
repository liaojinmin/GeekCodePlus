package me.GeekCodePlus.Command;

import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.Configure.LoadConfig;
import me.GeekCodePlus.Configure.LoadLang;
import me.GeekCodePlus.GeekCodeMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandReload {

    public static void execute(CommandSender sender, Command command, String label, String[] args)
    {
        if (!sender.hasPermission("geekc.admin"))
        {
            sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
            GeekCodeMain.say("§cgeekc.admin");
            return;
        }
        if (args[0].equals("reload"))
        {
            sender.sendMessage("§8[§3§lGeekCodePlus§8] §a配置文件|语言文件重载完成");
            LoadConfig.onLoad();
            LoadLang.onLoad();
        }
    }
}
