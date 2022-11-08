package me.GeekCodePlus;

import me.GeekCodePlus.Command.CommandCore;
import me.GeekCodePlus.Configure.LoadConfig;
import me.GeekCodePlus.Configure.LoadLang;
import me.GeekCodePlus.Libraries.LibrariesClassLoader;
import me.GeekCodePlus.Libraries.LibrariesManage;
import me.GeekCodePlus.Libraries.data.DataBaseManage;
import me.GeekCodePlus.Metrics.Metrics;
import me.GeekCodePlus.Module.ActionCode.ActivationActionManage;
import me.GeekCodePlus.Module.ActionCode.InviteActionManage;
import me.GeekCodePlus.Module.ModuleManage;
import me.GeekCodePlus.Module.ActionCode.ShareActionManage;
import me.GeekCodePlus.events.InviteCode;
import me.GeekCodePlus.utils.PlaceholderAPI.papi_Hook;
import me.GeekCodePlus.utils.RandomCode;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public class GeekCodeMain extends JavaPlugin {

    public static Economy econ = null;
    public static GeekCodeMain instance;
    public final static LibrariesClassLoader ClassLoader = new LibrariesClassLoader();
    public static final String Version = String.valueOf(3.0);

    public static ActivationActionManage activationActionManage;
    public static InviteActionManage inviteActionManage;
    public static ShareActionManage shareActionManage;
    public static RandomCode randomCode;


    @Override
    public void onLoad() {
        instance = this;
        motd("");
        motd("正在加载 §3§lGeekCodePlus §f...  §8" + Bukkit.getVersion());
        motd("");
    }


    @Override
    public void onEnable() {
        motd("");
        motd("  ________               __   _________            .___    __________.__");
        motd(" /  _____/  ____   ____ |  | _\\_   ___ \\  ____   __| _/____\\______   \\  |  __ __  ______");
        motd("/   \\  ____/ __ \\_/ __ \\|  |/ /    \\  \\/ /  _ \\ / __ |/ __ \\|     ___/  | |  |  \\/  ___/");
        motd("\\    \\_\\  \\  ___/\\  ___/|    <\\     \\___(  <_> ) /_/ \\  ___/|    |   |  |_|  |  /\\___ \\");
        motd(" \\______  /\\___  >\\___  >__|_ \\\\______  /\\____/\\____ |\\___  >____|   |____/____//____  >");
        motd("        \\/     \\/     \\/     \\/       \\/            \\/    \\/                         \\/");
        motd("");
        motd("     §aGeekCodePlus §bVersion:" + Version + " §7by §awww.geekcraft.ink");
        motd("     §8适用于Bukkit: §71.7.10-1.18.2 §8当前: §7" + Bukkit.getName());
        motd("");

        LoadConfig.onLoad();
        LoadLang.onLoad();
        new LibrariesManage();
        new ModuleManage();
        randomCode = new RandomCode();

        if (setupEconomy()) {
            say("§8找到 §7Vault §8挂钩!");
        }

        Objects.requireNonNull(getCommand("geekc")).setExecutor(new CommandCore());
        getServer().getPluginManager().registerEvents(new InviteCode(), this);
        new Metrics(this, 14238);

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            say("§8找到 §7PlaceholderAPI §8挂钩!");
            new papi_Hook(this).register();
        }

    }

    @Override
    public void onDisable() {
        say("§8[§3§lGeekCodePlus§8] §a再见！");
        DataBaseManage.closeDatabases();
    }


    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(net.milkbowl.vault.economy.Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public static void say(String s) {
        /*CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage("§8[§3§lGeekCode§b§lPlus§8] "+s.replace("&","§"));*/
        instance.getLogger().info(ChatColor.translateAlternateColorCodes('&', "§8[§3§lGeekCode§b§lPlus§8] " + s));
    }

    public static void motd(String s) {
        /*CommandSender sender = Bukkit.getConsoleSender();
        sender.sendMessage(s);*/
        instance.getLogger().info(s);
    }
}
