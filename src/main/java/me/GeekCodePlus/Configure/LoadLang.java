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
        long start = System.currentTimeMillis();
        saveLangConfig();
        language = YamlConfiguration.loadConfiguration(new File(new File(getDataFolder(), "lang"), "zh_CN.yml"));
        LangManage.PLUGIN_NAME = getString("PLUGIN_NAME");
        LangManage.NOT_PERM = getString("NOT_PERM");
        LangManage.HELP = getStringList("HELP");
        LangManage.USER_CDK_HELP = getStringList("USER_CDK_HELP");
        //邀请码模块
        String ModuleInv = "Module.InviteCode";
        LangManage.NOT_MONEY = getStringsList(ModuleInv + ".NOT_MONEY");
        LangManage.NOT_REWARD = getStringsList(ModuleInv + ".NOT_REWARD");
        LangManage.INVITE_HELP = getStringsList(ModuleInv + ".INVITE_HELP");
        LangManage.ME_CODE = getStringsList(ModuleInv + ".ME_CODE");
        LangManage.BUY_CDK = getStringsList(ModuleInv + ".BUY_CDK");
        LangManage.OWNER_REWARD_OUT = getStringsList(ModuleInv + ".OWNER_REWARD_OUT");
        LangManage.USER_REWARD_OUT = getStringsList(ModuleInv + ".USER_REWARD_OUT");
        LangManage.CODE_INVALID = getStringsList(ModuleInv + ".CODE_INVALID");
        LangManage.THIS_CODE_USED = getStringsList(ModuleInv + ".THIS_CODE_USED");
        LangManage.NOT_THIS_CODE = getStringsList(ModuleInv + ".NOT_THIS_CODE");
        LangManage.NOT_USER_YOU_CODE = getStringsList(ModuleInv + ".NOT_USER_YOU_CODE");
        //激活码模块
        String ModuleActi = "Module.ActivationCode.";
        LangManage.CODE_INVALID_2 = getStringsList(ModuleActi + "CODE_INVALID_2");
        LangManage.NOT_THIS_CODE_2 = getStringsList(ModuleActi + "NOT_THIS_CODE_2");
        LangManage.NOT_PERM_USER = getStringsList(ModuleActi + "NOT_PERM_USER");
        LangManage.CODE_LIMIT = getStringsList(ModuleActi + "CODE_LIMIT");
        LangManage.THIS_CODE_USED_2 = getStringsList(ModuleActi + "THIS_CODE_USED_2");
        LangManage.CODE_REWARD_OUT = getStringsList(ModuleActi + "CODE_REWARD_OUT");
        //分享码模块

        String ModuleShare = "Module.ShareCode";
        LangManage.CODE_PUT = getStringsList(ModuleShare + ".Code_Put");
        LangManage.CODE_GET = getStringsList(ModuleShare + ".Code_Get");
        LangManage.IS_MAIN = getStringsList(ModuleShare + ".ISMAIN");
        LangManage.ME_CODE_SHARE = getStringsList(ModuleShare + ".MECODE");
        LangManage.ME_CODE_NULL = getStringsList(ModuleShare + ".MECODE_NULL");
        LangManage.ME_CODE_UPDATE = getStringsList(ModuleShare + ".MECODE_UPDATA");
        long end = System.currentTimeMillis();
        GeekCodeMain.say("语言已加载,耗时: " + String.valueOf(end - start) + "ms");
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
        File dir = new File(getDataFolder(), "lang");
        if (!dir.exists()) dir.mkdirs();
        File lang = new File(dir, "zh_CN.yml");
        if (!lang.exists()) {
            GeekCodeMain.instance.saveResource("Lang" + File.separator + "zh_CN.yml", false);
        }
    }
}
