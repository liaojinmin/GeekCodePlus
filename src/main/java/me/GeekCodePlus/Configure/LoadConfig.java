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
        GeekCodeMain.instance.saveDefaultConfig();
        yml = new File(getDataFolder(), "config.yml");
        CHECK_UPDATE = getConfig().getBoolean("checkUpdate", true);
        //sql
        USE_TYPE = getConfig().getString("data_storage.use");
        HOST = getConfig().getString("data_storage.mysql.host");
        PORT = getConfig().getInt("data_storage.mysql.port");
        DATA_BASE = getConfig().getString("data_storage.mysql.database");
        USER_NAME = getConfig().getString("data_storage.mysql.username");
        PASS_WORD = getConfig().getString("data_storage.mysql.password");
        PARAMS = getConfig().getString("data_storage.mysql.params");
        //sql cp
        MAXIMUM_POOL_SIZE = getConfig().getInt("data_storage.hikari_settings.maximum_pool_size");
        MINIMUM_IDLE = getConfig().getInt("data_storage.hikari_settings.minimum_idle");
        MAXIMUM_LIFETIME = getConfig().getInt("data_storage.hikari_settings.maximum_lifetime");
        KEEPALIVE_TIME = getConfig().getInt("data_storage.hikari_settings.keepalive_time");
        CONNECTION_TIMEOUT = getConfig().getInt("data_storage.hikari_settings.connection_timeout");
        IDLE_TIMEOUT = getConfig().getInt("data_storage.hikari_settings.idle_timeout");

        //邀请码模块
        USER_INVITE_CODE = getConfig().getBoolean("Module.InviteCode.Use", true);
        BUY_CDK_MONEY = getConfig().getInt("Module.InviteCode.Buy_Cdk_Money");
        LIMIT_USE = getConfig().getBoolean("Module.InviteCode.Limit_usage");
        OWNER_REWARD = getConfig().getList("Module.InviteCode.Reward_settings.owner_reward");
        USER_REWARD = getConfig().getList("Module.InviteCode.Reward_settings.user_reward");

        //激活码模块
        USER_ACTIVATION_CODE = getConfig().getBoolean("Module.ActivationCode.Use", true);

        //分享码模块
        USER_SHARE_CODE = getConfig().getBoolean("Module.ShareCode.Use", true);
        CODE_LENGTH = getConfig().getInt("Module.ShareCode.length", 10);
        GUI_NAME = getStrings("Module.ShareCode.GUI_NAME");
        ICON_OK_MATERIAL = getConfig().getString("Module.ShareCode.Buttons.ICON_OK.Material", "MINECART");
        ICON_OK_NAME = getStrings("Module.ShareCode.Buttons.ICON_OK.Name");
        ICON_CAN_MATERIAL = getConfig().getString("Module.ShareCode.Buttons.ICON_CAN.Material", "BARRIER");
        ICON_CAN_NAME = getStrings("Module.ShareCode.Buttons.ICON_CAN.Name");
        ICON_MAIN_LORE = getStringList("Module.ShareCode.Buttons.ICON_MAIN.lore");
    }

    /**
     *
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
