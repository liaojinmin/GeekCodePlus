package me.GeekCodePlus.Command;

import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class CommandHelp {

    public static void execute(CommandSender sender, Command command, String label, String[] args)
    {
        if (!sender.hasPermission("geekc.command.help"))
        {
            sender.sendMessage(LangManage.PLUGIN_NAME + LangManage.NOT_PERM);
            GeekCodeMain.say("§cgeekc.command.help");
            return;
        }
        if (args.length == 0 || args[0].equals("help"))
        {
            for(String list_to_string : LangManage.HELP)
            {
                sender.sendMessage(list_to_string);
            }
        }
    }
}
