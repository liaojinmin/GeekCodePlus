package me.GeekCodePlus.Module.ActivationCode;

import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Libraries.data.DataBaseManage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public final class ActivationActionManage {

    public void SaveCode(Player player, String command, int amount, String perm) {

        String cmd = command.replace("+"," ");
        String code = GeekCodeMain.randomCode.getRandomGeekA();
        Activation_DataManage.setActivationData(player,cmd,amount,perm,code);
    }

    public void GIveReward(Player player, String command) {
        String cmd = command.replace("[player_name]", Objects.requireNonNull(player.getPlayer()).getName());
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
        for (String out : LangManage.CODE_REWARD_OUT) {
            player.sendMessage(out);
        }
    }

    private static String COMMAND;
    private static int LAVE;
    private static String PERM;


    /**
     * 查询数据库，是否有改激活码，如果有 调用插入数据并发放奖励
     * @param player 玩家对象
     * @param name 玩家名
     * @param code 兑换码
     */
    public synchronized void UserCode(Player player, String name, String code) {
        boolean isMatch = Pattern.matches("GeekA-[A-Z]{4}-[A-Z]{4}-[A-Z]{4}-[A-Z]{4}$",code);
        if (!isMatch) {
            //格式错误
            for (String out : LangManage.CODE_INVALID_2) {
                player.sendMessage(out);
            }
        }
        int index = Activation_DataManage.getActivationUserMap.size();
        if (!isNoUser(index, code, name)) {

            try (Connection connection = DataBaseManage.getConnection()) {
                try (PreparedStatement s = connection.prepareStatement("SELECT * FROM geek_activation_data WHERE code=?;")) {
                    s.setString(1, code);
                    ResultSet res = s.executeQuery();
                    if (!res.isBeforeFirst()) {
                        for (String out : LangManage.NOT_THIS_CODE_2) {
                            player.sendMessage(out);
                        }
                    } else {
                        while (res.next()) {
                            COMMAND = res.getString("command");
                            LAVE = res.getInt("lave");
                            PERM = res.getString("perm");
                        }
                        if (!player.hasPermission(PERM)) {
                            //玩家没用权限使用
                            for (String out : LangManage.NOT_PERM_USER) {
                                player.sendMessage(out);
                            }
                            return;
                        }
                        if (LAVE <= 0) {
                            //玩家使用次数上限了
                            for (String out : LangManage.CODE_LIMIT) {
                                player.sendMessage(out);
                            }
                            return;
                        }
                        //上库
                        Activation_DataManage.setUserData(player, name, code, LAVE, COMMAND, (index + 1));
                        //给予奖励
                        GIveReward(player, COMMAND);
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return;
        }
        //这个激活码已经使用过
        for (String out : LangManage.THIS_CODE_USED_2) {
            player.sendMessage(out);
        }
    }


    /*
    检查玩家是否使用过这个Code
    如果已经使用过 返回 true
     */
    public boolean isNoUser(int index, String s, String name) {
        if (Activation_DataManage.getActivationUserMap.isEmpty()) {
            return false;
        }
        for (int i = 1; i <= index; i++) {
            Activation_DataManage.ActivationUserObj MapData = Activation_DataManage.getActivationUserMap.get(i);
            if (MapData.getCODE().equals(s) && MapData.getNAME().equals(name)) {
                return true;
            }
        }
        return false;
    }

}
