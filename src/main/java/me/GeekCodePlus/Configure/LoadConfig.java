package me.GeekCodePlus.Configure;

import com.google.common.base.Joiner;
import me.GeekCodePlus.GeekCodeMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public final class LoadConfig extends ConfigManage {

    private static File yml;

    public static void onLoad() {
        GeekCodeMain.say("§8加载配置管理器...");
        long start = System.currentTimeMillis();
        GeekCodeMain.instance.saveDefaultConfig();
        yml = new File(getDataFolder(), "config.yml");
        CHECK_UPDATE = getConfig().getBoolean("checkUpdate", true);
        //sql
        String DataMySQL = "data_storage.mysql";
        USE_TYPE = getConfig().getString("data_storage.use");
        HOST = getConfig().getString(DataMySQL + ".host");
        PORT = getConfig().getInt(DataMySQL + ".port");
        DATA_BASE = getConfig().getString(DataMySQL + ".database");
        USER_NAME = getConfig().getString(DataMySQL + ".username");
        PASS_WORD = getConfig().getString(DataMySQL + ".password");
        PARAMS = getConfig().getString(DataMySQL + ".params");
        //sql cp
        String DataSQLIte = "data_storage.hikari_settings";
        MAXIMUM_POOL_SIZE = getConfig().getInt(DataSQLIte + ".maximum_pool_size");
        MINIMUM_IDLE = getConfig().getInt(DataSQLIte + ".minimum_idle");
        MAXIMUM_LIFETIME = getConfig().getInt(DataSQLIte + ".maximum_lifetime");
        KEEPALIVE_TIME = getConfig().getInt(DataSQLIte + ".keepalive_time");
        CONNECTION_TIMEOUT = getConfig().getInt(DataSQLIte + ".connection_timeout");
        IDLE_TIMEOUT = getConfig().getInt(DataSQLIte + ".idle_timeout");

        //邀请码模块
        String ModuleInv = "Module.InviteCode";
        USER_INVITE_CODE = getConfig().getBoolean(ModuleInv + ".Use", true);
        BUY_CDK_MONEY = getConfig().getInt(ModuleInv + ".Buy_Cdk_Money");
        LIMIT_USE = getConfig().getBoolean(ModuleInv + ".Limit_usage");
        OWNER_REWARD = getConfig().getList(ModuleInv + ".Reward_settings.owner_reward");
        USER_REWARD = getConfig().getList(ModuleInv + ".Reward_settings.user_reward");

        //激活码模块
        USER_ACTIVATION_CODE = getConfig().getBoolean("Module.ActivationCode.Use", true);

        //分享码模块
        String ModuleShare = "Module.ShareCode";
        USER_SHARE_CODE = getConfig().getBoolean(ModuleShare + ".Use", true);
        CODE_LENGTH = getConfig().getInt(ModuleShare + ".length", 10);
        GUI_NAME = getStrings(ModuleShare + ".GUI_NAME");
        ICON_OK_MATERIAL = getConfig().getString(ModuleShare + ".Buttons.ICON_OK.Material", "MINECART");
        ICON_OK_NAME = getStrings(ModuleShare + ".Buttons.ICON_OK.Name");
        ICON_CAN_MATERIAL = getConfig().getString(ModuleShare + ".Buttons.ICON_CAN.Material", "BARRIER");
        ICON_CAN_NAME = getStrings(ModuleShare + ".Buttons.ICON_CAN.Name");
        ICON_MAIN_LORE = getStringList(ModuleShare + ".Buttons.ICON_MAIN.lore");
        long end = System.currentTimeMillis();
        GeekCodeMain.say("配置已加载完成,耗时: "+ String.valueOf(end-start)+"ms");
    }

    /**
     * @return 获取配置文件目录
     */
    public static FileConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(yml);
    }

    private static String getStrings(@NotNull String s) {
        return getConfig().getString(s).replace("&", "§");
    }


    private static List<String> getStringList(@NotNull String s) {
        return Arrays.asList(Joiner.on(",").join(getConfig().getStringList(s)).replace("&", "§").split(","));
    }
}
