package me.GeekCodePlus.Module.InviteCode;

import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataBaseManage;
import me.GeekCodePlus.utils.PlaceholderAPI.papiDataHead;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.*;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Invite_DataManage {

    public static Map<Integer, InviteOwnerObj> getOwnerMap = new ConcurrentHashMap<>();
    public static Map<Integer, InviteUserObj> getUserMap = new ConcurrentHashMap<>();


    public static void setOwnerNewData(Player p, String name, String uuid, int in) {
        try (Connection connection = DataBaseManage.getConnection()) {
            try (PreparedStatement s = connection.prepareStatement("INSERT INTO geek_invite_data(`owner_name`,`owner_uuid`,`owner_cdk`) VALUES(?,?,?);")) {
                String cdk = GeekCodeMain.randomCode.getRandomGeekI();
                s.setString(1, name);
                s.setString(2, uuid);
                s.setString(3, cdk);
                s.execute();
                Invite_DataManage.getOwnerMap.put(in, new Invite_DataManage.InviteOwnerObj(name, uuid, cdk, 0, 0));
                papiDataHead.setInviteOwnerMap(name, uuid, cdk, 0, 0);
                TextComponent text = new TextComponent("    §7[§A点击复制§7]");
                text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, cdk));
                for (String out : LangManage.BUY_CDK) {
                    p.sendMessage(out.replace("[cdk]", cdk));
                }
                p.spigot().sendMessage(text);
                p.sendMessage("");
                p.sendMessage("");
            }
        } catch (SQLException e) {
            p.sendMessage(LangManage.PLUGIN_NAME + "§c§lNO mysql err");
            e.printStackTrace();
        }
    }

    public static void setUserAndOwner(Player player, String cdk, String Owner_name, String Owner_uuid, int Owner_count, int Owner_reward, String ip, int in) {
        String uuid = String.valueOf(Objects.requireNonNull(player.getPlayer()).getUniqueId());
        String name = player.getName();
        int index = (Invite_DataManage.getUserMap.size() + 1);
        try (Connection connection = DataBaseManage.getConnection()) {
            try (PreparedStatement sa = connection.prepareStatement("INSERT INTO geek_invite_user_data(`user_name`,`user_uuid`,`user_cdk`,`user_ip`) VALUES(?,?,?,?);");
                 PreparedStatement sb = connection.prepareStatement("UPDATE `geek_invite_data` SET `owner_count`=?, `owner_reward`=? WHERE owner_name=?;")) {
                sa.setString(1, name);
                sa.setString(2, uuid);
                sa.setString(3, cdk);
                sa.setString(4, ip);
                sa.execute();
                sb.setInt(1, Owner_count);
                sb.setInt(2, Owner_reward);
                sb.setString(3, Owner_name);
                sb.executeUpdate();
                //更新使用者缓存
                Invite_DataManage.getUserMap.put(index, new InviteUserObj(name, uuid, cdk, ip));
                //更新拥有者缓存
                Invite_DataManage.getOwnerMap.put(in, new InviteOwnerObj(Owner_name, Owner_uuid, cdk, Owner_count, Owner_reward));
                //更新变量拥有者嘻哈图
                papiDataHead.setInviteOwnerMap(Owner_name, Owner_uuid, cdk, Owner_count, Owner_reward);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void setOwnerData(Player player, String Owner_name, int Owner_Reward) {
        try (Connection connection = DataBaseManage.getConnection()) {
            try (PreparedStatement s = connection.prepareStatement("UPDATE `geek_invite_data` SET `owner_reward`=? WHERE owner_name=?;")) {
                s.setInt(1, Owner_Reward);
                s.setString(2, Owner_name);
                s.executeUpdate();
                for (String out : LangManage.OWNER_REWARD_OUT) {
                    player.sendMessage(out);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void INVITE_getData() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection connection = DataBaseManage.getConnection()) {
                    try (Statement statement = connection.createStatement()) {
                        ResultSet res = statement.executeQuery("SELECT  * FROM `geek_invite_data` WHERE id");
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        getOwnerMap.clear();
                        int index = 1;
                        while (res.next()) {
                            String Name = res.getString("owner_name");
                            String Uuid = res.getString("owner_uuid");
                            String Cdk = res.getString("owner_cdk");
                            int Count = res.getInt("owner_count");
                            int Reward = res.getInt("owner_reward");
                            getOwnerMap.put(index, new InviteOwnerObj(Name, Uuid, Cdk, Count, Reward));
                            papiDataHead.setInviteOwnerMap(Name, Uuid, Cdk, Count, Reward);
                            index++;
                        }
                    }
                } catch (SQLException e) {
                    GeekCodeMain.say("&c邀请码-拥有者数据转储失败");
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(GeekCodeMain.instance);
    }

    public static void INVITE_getUserData() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection connection = DataBaseManage.getConnection()) {
                    try (Statement statement = connection.createStatement()) {
                        ResultSet res = statement.executeQuery("SELECT  * FROM `geek_invite_user_data` WHERE id");
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        getUserMap.clear();
                        int index = 1;
                        while (res.next()) {
                            String Name = res.getString("user_name");
                            String Uuid = res.getString("user_uuid");
                            String Cdk = res.getString("user_cdk");
                            String Ip = res.getString("user_ip");
                            getUserMap.put(index, new InviteUserObj(Name, Uuid, Cdk, Ip));
                            index++;
                        }
                    }
                } catch (SQLException e) {
                    GeekCodeMain.say("&c邀请码-使用者数据转储失败");
                    e.printStackTrace();
                }
            }
        }.runTaskAsynchronously(GeekCodeMain.instance);
    }

    static class InviteOwnerObj {
        private String OWNER_NAME;
        private String OWNER_UUID;
        private String OWNER_CDK;
        private int OWNER_COUNT;
        private int OWNER_REWARD;

        /**
         * 这是邀请码拥有者的缓存数据构造函数
         *
         * @param name   玩家名称
         * @param uuid   玩家uuid
         * @param cdk    cdk
         * @param count  被使用次数
         * @param reward 剩余的奖励次数
         */
        public InviteOwnerObj(String name, String uuid, String cdk, int count, int reward) {
            this.OWNER_NAME = name;
            this.OWNER_UUID = uuid;
            this.OWNER_CDK = cdk;
            this.OWNER_COUNT = count;
            this.OWNER_REWARD = reward;
        }

        public String getOWNER_NAME() {
            return OWNER_NAME;
        }

        public String getOWNER_UUID() {
            return OWNER_UUID;
        }

        public String getOWNER_CDK() {
            return OWNER_CDK;
        }

        public int getOWNER_COUNT() {
            return OWNER_COUNT;
        }

        public int getOWNER_REWARD() {
            return OWNER_REWARD;
        }
    }

    static class InviteUserObj {
        private String USER_NAME;
        private String USER_UUID;
        private String USER_CDK;
        private String USER_IP;

        /**
         * 这是邀请码使用者的缓存数据构造函数
         *
         * @param name 邀请码使用者的名称
         * @param uuid 邀请码使用者的uuid
         * @param cdk  邀请码使用者，使用的 cdk
         * @param ip   邀请码使用者的 ip
         */
        public InviteUserObj(String name, String uuid, String cdk, String ip) {
            this.USER_NAME = name;
            this.USER_UUID = uuid;
            this.USER_CDK = cdk;
            this.USER_IP = ip;
        }

        public String getUSER_CDK() {
            return USER_CDK;
        }

        public String getUSER_IP() {
            return USER_IP;
        }

        public String getUSER_NAME() {
            return USER_NAME;
        }

        public String getUSER_UUID() {
            return USER_UUID;
        }
    }
}
