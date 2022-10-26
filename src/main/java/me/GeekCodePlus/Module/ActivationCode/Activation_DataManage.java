package me.GeekCodePlus.Module.ActivationCode;

import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataBaseManage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public final class Activation_DataManage {

    public static Map<Integer, ActivationObj> getActivationMap = new ConcurrentHashMap<>();
    public static Map<Integer, ActivationUserObj> getActivationUserMap = new ConcurrentHashMap<>();

    /**
     * 玩家使用code后将记录保存，并修改code原表
     * @param player 玩家对象
     * @param name 玩家名字
     * @param code code码
     * @param lave 剩余次数
     * @param command 指令
     * @param index map索
     */
    public static void setUserData(Player player, String name, String code, int lave, String command, int index) {
        int a = 1;
        int b = lave - a;
        try (Connection c = DataBaseManage.getConnection()) {
            try (PreparedStatement sa = c.prepareStatement("INSERT INTO geek_activation_user_data(`name`,`code`,`command`) VALUES(?,?,?);");
                 PreparedStatement sb = c.prepareStatement("UPDATE `geek_activation_data` SET `lave`=? WHERE code=?;")) {
                sa.setString(1, name);
                sa.setString(2, code);
                sa.setString(3, command);
                sb.setInt(1, b);
                sb.setString(2, code);
                //先更新本地缓存，再执行数据插入
                getActivationUserMap.put(index, new ActivationUserObj(name, code, command));
                sa.execute();
                sb.executeUpdate();
            }
        } catch (SQLException E) {
            E.printStackTrace();
        }
    }

    /**
     * 将生成的激活码保存至数据库
     * @param player 玩家对象
     * @param command 执行的指令
     * @param amount 可使用次数
     * @param perm 使用权限
     * @param code code码
     */
    public static void setActivationData(Player player, String command, int amount, String perm, String code) {
        try (Connection c = DataBaseManage.getConnection()) {
            try (PreparedStatement s = c.prepareStatement("INSERT INTO geek_activation_data(`code`,`lave`,`command`,`perm`) VALUES(?,?,?,?)")) {
                s.setString(1, code);
                s.setInt(2, amount);
                s.setString(3, command);
                s.setString(4, perm);
                s.execute();
                int index = Activation_DataManage.getActivationMap.size() + 1;
                Activation_DataManage.getActivationMap.put(index, new ActivationObj(code, amount, command, perm));
                TextComponent text = new TextComponent(LangManage.PLUGIN_NAME + " §e§l成功生成兑换码§8-§f" + code + "  §7[§A点击复制§7]");
                text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, code));
                player.spigot().sendMessage(text);
            }
        } catch (SQLException e) {
            player.sendMessage(LangManage.PLUGIN_NAME + "§C数据库错误，请参考控制台获取详细信息");
            e.printStackTrace();
        }

    }

    /**
     * 主动获取数据库中所有的激活码，
     */
    public static void ACTIVATION_getData(){
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection c = DataBaseManage.getConnection()) {
                    try (Statement s = c.createStatement()) {
                        ResultSet res = s.executeQuery("SELECT * FROM `geek_activation_data` WHERE id");
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        getActivationMap.clear();
                        int index = 1;
                        while (res.next()) {
                            String Code = res.getString("code");
                            int Lave = res.getInt("lave");
                            String Command = res.getString("command");
                            String Perm = res.getString("perm");
                            getActivationMap.put(index, new ActivationObj(Code, Lave, Command, Perm));
                            index++;
                        }
                    }
                } catch (Exception e) {
                    GeekCodeMain.say("&c激活码数据转储失败");
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(GeekCodeMain.instance);
    }

    /**
     * 主动获取数据库中激活码使用者的全部数据
     */
    public static void ACTIVATION_getUserData(){
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection c = DataBaseManage.getConnection()) {
                    try (Statement s = c.createStatement()) {
                        ResultSet res = s.executeQuery("SELECT * FROM `geek_activation_user_data` WHERE id");
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        getActivationUserMap.clear();
                        int index = 1;
                        while (res.next()) {
                            String Name = res.getString("name");
                            String Code = res.getString("code");
                            String Command = res.getString("command");
                            getActivationUserMap.put(index, new ActivationUserObj(Name, Code, Command));
                            index++;
                        }
                    }
                } catch (Exception e) {
                    GeekCodeMain.say("§c激活码使用者数据转储失败");
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(GeekCodeMain.instance);
    }

    static class ActivationObj {
        private String CODE;
        private int LAVE;
        private String COMMAND;
        private String PERM;

        /**
         * 设置邀请码数据对象
         * @param code 兑换码
         * @param lave 剩余使用次数
         * @param command 执行的指令
         * @param perm 需要权限
         */
        public ActivationObj(String code, int lave, String command, String perm) {
            this.CODE = code;
            this.LAVE = lave;
            this.COMMAND = command;
            this.PERM = perm;
        }
        public String getCODE() {
            return CODE;
        }

        public int getLAVE() {
            return LAVE;
        }

        public String getCOMMAND() {
            return COMMAND;
        }

        public String getPERM() {
            return PERM;
        }
    }

    static class ActivationUserObj {
        private String NAME;
        private String CODE;
        private String COMMAND;

        /**
         * * 设置邀请码数据对象
         * * @param name 兑换码
         * * @param code 剩余使用次数
         * * @param command 执行的指令
         * */
        public ActivationUserObj(String name, String code, String command) {
            this.NAME = name;
            this.CODE = code;
            this.COMMAND = command;
        }

        public String getNAME() {
            return NAME;
        }

        public String getCODE() {
            return CODE;
        }

        public String getCOMMAND() {
            return COMMAND;
        }
    }

}
