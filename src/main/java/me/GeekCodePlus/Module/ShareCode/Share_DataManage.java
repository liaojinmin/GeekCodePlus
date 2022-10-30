package me.GeekCodePlus.Module.ShareCode;

import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataBaseManage;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Share_DataManage {
    //本地缓存
    public static Map<String, ShareObj> getCodeMap = new ConcurrentHashMap<>();
    //个人已创建并且未使用的分享码
    public static Map<String, List<String>> getMeCodeMap = new ConcurrentHashMap<>();


    /**
     *
     * @param server_name 服务器名称
     * @param item_name 物品
     * @param code cdk
     * @param upload_playname 上传者名称
     * @param use_status 使用状态 （未使用）（已使用）
     */
    public static void insert(String server_name, String item_stack, String item_name, int amount, String code, String upload_playname, String use_status) {
        Date date = new Date();
        SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try (Connection c = DataBaseManage.getConnection()) {
            try (PreparedStatement s = c.prepareStatement("INSERT INTO geek_share_data(`server_name`,`item_stack`,`item_name`,`item_amount`,`code`,`upload_playname`,`use_status`) VALUES(?,?,?,?,?,?,?);")) {
                s.setString(1, server_name);
                s.setString(2, item_stack);
                s.setString(3, item_name);
                s.setInt(4, amount);
                s.setString(5, code);
                s.setString(6, upload_playname);
                s.setString(7, use_status);
                s.execute();
                getCodeMap.put(code, new ShareObj(server_name, item_stack, item_name, amount, code, si.format(date), upload_playname, use_status, "null", "null"));
            }
        } catch (SQLException e) {
            GeekCodeMain.say("§c数据储存发生错误-Share-insert");
            e.printStackTrace();
        }
    }

    public static void Update(String server_name, String item_stack, String item_name, String code, String upload_time, String upload_playname, String use_playname) {
        try (Connection connection = DataBaseManage.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE `geek_share_data` SET `use_status`=?, `use_playname`=?, `use_time`=? WHERE `code`=?;")) {
                java.util.Date d = new Date();
                SimpleDateFormat si = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                statement.setString(1, "已使用");
                statement.setString(2, use_playname);
                statement.setString(3, si.format(d));
                statement.setString(4, code);
                statement.executeUpdate();
                getCodeMap.put(code, new ShareObj(server_name, item_stack, item_name, 0, code, upload_time, upload_playname, "已使用", use_playname, si.format(d)));
            }
        } catch (SQLException e) {
            GeekCodeMain.say("数据库错误-Share-setUpdate");
            e.printStackTrace();
        }
    }

    public static void getShareData() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection connection = DataBaseManage.getConnection()) {
                    try (Statement statement = connection.createStatement()) {
                        ResultSet res = statement.executeQuery("SELECT * FROM `geek_share_data` WHERE id");
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        getCodeMap.clear();
                        while (res.next()) {
                            ShareObj a = new ShareObj(
                                    res.getString("server_name"),
                                    res.getString("item_stack"),
                                    res.getString("item_name"),
                                    res.getInt("item_amount"),
                                    res.getString("code"),
                                    res.getString("upload_time"),
                                    res.getString("upload_playname"),
                                    res.getString("use_status"),
                                    res.getString("use_playname"),
                                    res.getString("use_time"));
                            getCodeMap.put(res.getString("code"), a);
                        }
                    }
                } catch (SQLException e) {
                    GeekCodeMain.say("§c数据更新任务失败-getShareData");
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(GeekCodeMain.instance);
    }

    public static void getShareMeData(String name) {
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection connection = DataBaseManage.getConnection()) {
                    try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM `geek_share_data` WHERE upload_playname=? AND use_status=?;")) {
                        statement.setString(1, name);
                        statement.setString(2, "未使用");
                        ResultSet res = statement.executeQuery();
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        List<String> list = new ArrayList<>();
                        while (res.next()) {
                            list.add(res.getString("code"));
                        }
                        getMeCodeMap.put(name, list);
                    }
                } catch (SQLException e) {
                    GeekCodeMain.say("§c数据更新任务失败-getShareMeData");
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(GeekCodeMain.instance);
    }

    public static class ShareObj {
        private String SERVER_NAME;
        private String ITEM_STACK;
        private String ITEM_NAME;
        private int ITEM_AMOUNT;
        private String CODE;
        private String UPLOAD_TIME;
        private String UPLOAD_PLAYNAME;
        private String USE_STATUS;
        private String USE_PLAYNAME;
        private String USE_TIME;

        public ShareObj(String SERVER_NAME, String ITEM_STACK, String ITEM_NAME, int ITEM_AMOUNT, String CODE, String UPLOAD_TIME, String UPLOAD_PLAYNAME, String USE_STATUS, String USE_PLAYNAME, String USE_TIME) {
            this.SERVER_NAME = SERVER_NAME;
            this.ITEM_STACK = ITEM_STACK;
            this.ITEM_NAME = ITEM_NAME;
            this.ITEM_AMOUNT = ITEM_AMOUNT;
            this.CODE = CODE;
            this.UPLOAD_TIME = UPLOAD_TIME;
            this.UPLOAD_PLAYNAME = UPLOAD_PLAYNAME;
            this.USE_STATUS = USE_STATUS;
            this.USE_PLAYNAME = USE_PLAYNAME;
            this.USE_TIME = USE_TIME;
        }

        public String getSERVER_NAME() {
            return SERVER_NAME;
        }

        public String getITEM_STACK() {
            return ITEM_STACK;
        }

        public String getITEM_NAME() {
            return ITEM_NAME;
        }
        public int getITEM_AMOUNT() {
            return ITEM_AMOUNT;
        }

        public String getCODE() {
            return CODE;
        }

        public String getUPLOAD_TIME() {
            return UPLOAD_TIME;
        }
        public String getUPLOAD_PLAYNAME() {
            return UPLOAD_PLAYNAME;
        }
        public String getUSE_STATUS() {
            return USE_STATUS;
        }
        public String getUSE_PLAYNAME() {
            return USE_PLAYNAME;
        }

        public String getUSE_TIME() {
            return USE_TIME;
        }
    }

}
