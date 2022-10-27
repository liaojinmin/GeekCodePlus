package me.GeekCodePlus.Configure;

import com.google.common.base.Joiner;
import me.GeekCodePlus.GeekCodeMain;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public final class LoadLang extends LangManage {

    private static FileConfiguration language;


    public static void onLoad() {

        GeekCodeMain.say("§8加载语言管理器...");
        saveLangConfig();
        language = YamlConfiguration.loadConfiguration(new File(new File(getDataFolder(),"lang"), "zh_CN.yml"));
        LangManage.PLUGIN_NAME = getString("PLUGIN_NAME");
        LangManage.NOT_PERM = getString("NOT_PERM");
        LangManage.HELP = getStringList("HELP");
        LangManage.USER_CDK_HELP = getStringList("USER_CDK_HELP");
        //邀请码模块
        LangManage.NOT_MONEY = getStringsList("Module.InviteCode.NOT_MONEY");
        LangManage.NOT_REWARD = getStringsList("Module.InviteCode.NOT_REWARD");
        LangManage.INVITE_HELP = getStringsList("Module.InviteCode.INVITE_HELP");
        LangManage.ME_CODE = getStringsList("Module.InviteCode.ME_CODE");
        LangManage.BUY_CDK = getStringsList("Module.InviteCode.BUY_CDK");
        LangManage.OWNER_REWARD_OUT = getStringsList("Module.InviteCode.OWNER_REWARD_OUT");
        LangManage.USER_REWARD_OUT = getStringsList("Module.InviteCode.USER_REWARD_OUT");
        LangManage.CODE_INVALID = getStringsList("Module.InviteCode.CODE_INVALID");
        LangManage.THIS_CODE_USED = getStringsList("Module.InviteCode.THIS_CODE_USED");
        LangManage.NOT_THIS_CODE = getStringsList("Module.InviteCode.NOT_THIS_CODE");
        LangManage.NOT_USER_YOU_CODE = getStringsList("Module.InviteCode.NOT_USER_YOU_CODE");
        //激活码模块
        LangManage.CODE_INVALID_2 = getStringsList("Module.ActivationCode.CODE_INVALID_2");
        LangManage.NOT_THIS_CODE_2 = getStringsList("Module.ActivationCode.NOT_THIS_CODE_2");
        LangManage.NOT_PERM_USER = getStringsList("Module.ActivationCode.NOT_PERM_USER");
        LangManage.CODE_LIMIT = getStringsList("Module.ActivationCode.CODE_LIMIT");
        LangManage.THIS_CODE_USED_2 = getStringsList("Module.ActivationCode.THIS_CODE_USED_2");
        LangManage.CODE_REWARD_OUT = getStringsList("Module.ActivationCode.CODE_REWARD_OUT");
        //分享码模块
        LangManage.CODE_PUT = getStringsList("Module.ShareCode.Code_Put");
        LangManage.CODE_GET = getStringsList("Module.ShareCode.Code_Get");
        LangManage.IS_MAIN = getStringsList("Module.ShareCode.ISMAIN");
        LangManage.ME_CODE_SHARE = getStringsList("Module.ShareCode.MECODE");
        LangManage.ME_CODE_NULL = getStringsList("Module.ShareCode.MECODE_NULL");
        LangManage.ME_CODE_UPDATA = getStringsList("Module.ShareCode.MECODE_UPDATA");
    }


    public static FileConfiguration getLang() {
        return language;
    }
    private static @NotNull String getString(@NotNull String s) {
        return getLang().getString(s).replace("&", "§");
    }


    private static List<String> getStringList(@NotNull String s) {
        return Arrays.asList(Joiner.on(",").join(getLang().getStringList(s)).replace("&", "§").split(","));
    }
    private static List<String> getStringsList(@NotNull String s) {
        return Arrays.asList(getLang().getString(s).replace("&", "§").split("\n"));
    }
    private static void saveLangConfig() {
        File dir = new File(getDataFolder(),"lang");
        if (!dir.exists()) dir.mkdirs();
        File lang = new File(dir, "zh_CN.yml");
        if (!lang.exists()) {
            GeekCodeMain.instance.saveResource( "Lang" + File.separator + "zh_CN.yml",false);
        }
    }
}
