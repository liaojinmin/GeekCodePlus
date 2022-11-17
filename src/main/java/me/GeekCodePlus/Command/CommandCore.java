package me.GeekCodePlus.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class CommandCore implements CommandExecutor {


    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (args.length == 0) return false;
        /*if (args.length == 0 || args[0].equals("help")) {
            CommandHelp.execute(sender, command, label, args);
            return true;
        }
        if (args[0].equalsIgnoreCase("create")) {
            CommandCreate.execute(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("reload")) {
            CommandReload.execute(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("inv")) {
            CommandInvite.execute(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("cdk")) {
            CommandCdk.execute(sender, command, label, args);
        }
        if (args[0].equalsIgnoreCase("share")) {
            CommandShare.execute(sender, command, label, args);
        }*/

        switch (args[0].toLowerCase()){
            case "help":
                CommandHelp.execute(sender, command, label, args);
                return true;
            case "create":
                CommandCreate.execute(sender, command, label, args);
                return true;
            case "reload":
                CommandReload.execute(sender, command, label, args);
                return true;
            case "inv":
                CommandInvite.execute(sender, command, label, args);
                return true;
            case "cdk":
                CommandCdk.execute(sender, command, label, args);
                return true;
            case "share":
                CommandShare.execute(sender, command, label, args);
                return true;
            default:
                return false;
        }
    }
}
