package me.GeekCodePlus.Configure;

import me.GeekCodePlus.GeekCodeMain;

import java.io.File;
import java.util.List;

public class ConfigManage {


    public static File getDataFolder() {
        return GeekCodeMain.instance.getDataFolder();
    }
    public static boolean CHECK_UPDATE;
    //data_storage
    public static String USE_TYPE;
    public static String HOST;
    public static int PORT;
    public static String DATA_BASE;
    public static String USER_NAME;
    public static String PASS_WORD;
    public static String PARAMS;
    public static int MAXIMUM_POOL_SIZE;
    public static int MINIMUM_IDLE;
    public static int MAXIMUM_LIFETIME;
    public static int KEEPALIVE_TIME;
    public static int CONNECTION_TIMEOUT;
    public static int IDLE_TIMEOUT;

    //模块设置配置

    //邀请码模块
    public static boolean USER_INVITE_CODE;
    public static int BUY_CDK_MONEY;
    public static boolean LIMIT_USE;
    public static List<?> OWNER_REWARD;
    public static List<?> USER_REWARD;

    //激活码模块
    public static boolean USER_ACTIVATION_CODE;

    //分享码模块
    public static boolean USER_SHARE_CODE;
    public static int CODE_LENGTH;
    public static String GUI_NAME;
    public static String ICON_OK_MATERIAL;
    public static String ICON_OK_NAME;
    public static String ICON_CAN_MATERIAL;
    public static String ICON_CAN_NAME;
    public static List<String> ICON_MAIN_LORE;

}
