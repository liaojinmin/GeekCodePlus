package me.GeekCodePlus.utils.PlaceholderAPI;

import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataBaseManage;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class papiDataHead {

    public static Map<Integer, papiOwnerTopObj> getInviteTop = new ConcurrentHashMap<>();
    public static Map<String, papiOwnerObj> getOwnerData = new HashMap<>();

    public static void setInviteOwnerMap(String name, String uuid, String cdk, int count, int reward) {
        getOwnerData.put(name, new papiOwnerObj(name, uuid, cdk, count, reward));
    }

    public static void getInviteTop() {
        new BukkitRunnable() {
            @Override
            public void run() {
                try (Connection connection = DataBaseManage.getConnection()) {
                    try (Statement statement = connection.createStatement()) {
                        ResultSet res = statement.executeQuery("SELECT `owner_name`, `owner_count` FROM `geek_invite_data` ORDER BY owner_count DESC limit 10");
                        if (!res.isBeforeFirst()) {
                            return;
                        }
                        getInviteTop.clear();
                        int index = 1;
                        String name = res.getString("owner_name");
                        int count = res.getInt("owner_count");
                        papiOwnerTopObj papiOwnerTopObj = new papiOwnerTopObj(name, count);
                        while (res.next()) {
                            name = res.getString("owner_name");
                            count = res.getInt("owner_count");
                            papiOwnerTopObj.setO_NAME(name);
                            papiOwnerTopObj.setO_COUNT(count);
                            //papiOwnerTopObj a = new papiOwnerTopObj(name, count);
                            getInviteTop.put(index, papiOwnerTopObj);
                            index++;
                        }
                    }
                } catch (Exception e) {
                    GeekCodeMain.say("§c邀请码排行榜加载失败！");
                    e.printStackTrace();
                }
            }
        }.runTaskTimerAsynchronously(GeekCodeMain.instance, 60 * 20, 60 * 20);
    }

}
