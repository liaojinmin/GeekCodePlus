package me.GeekCodePlus.Module.InviteCode;

import com.google.common.base.Joiner;
import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.Configure.LangManage;
import me.GeekCodePlus.utils.PlaceholderAPI.papiDataHead;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;
import java.util.regex.Pattern;

public final class InviteActionManage {

    private String Owner_cdk;

    private String IsMe;
    //  private static String IsIp;
    public String OWNER_name;
    public String OWNER_code;
    private String OWNER_uuid;
    private int OWNER_count;
    private int OWNER_reward;
    private int INDEX = 0;
    private final int Owner_Reward = 0;
    private int REWARD = 0;


    public synchronized void UserCode(Player player, String name, String code) {
        String ip = Objects.requireNonNull(player.getAddress()).getAddress().getHostAddress();
        boolean isMatch = Pattern.matches("GeekI-[A-Za-z0-9]{4}-[0-9]{4}-[A-Za-z0-9]{4}$", code);
        if (!isMatch) {
            //格式错误
            for (String out : LangManage.CODE_INVALID) {
                player.sendMessage(out);
            }
            return;
        }
        if (isUsed(name, code, ip)) {
            //已使用过
            for (String out : LangManage.THIS_CODE_USED) {
                player.sendMessage(out);
            }
            return;
        }
        /*
        if (IsIp.equals("yes")) {
            player.sendMessage(Language.PLUGIN_NAME + "§C频繁使用小号，你的账号已被禁止使用邀请码！");
        }*/
        if(isExist(name, code)) {
            OWNER_code = code;
            Invite_DataManage.setUserAndOwner(player, code, OWNER_name, OWNER_uuid, OWNER_count, OWNER_reward, ip, INDEX);
            getUserReward(player, name);
            for (String out : LangManage.USER_REWARD_OUT) {
                player.sendMessage(out.replace("[cdk]", code).replace("[owner_name]", OWNER_name));
            }
            return;
        }
        switch (IsMe) {
            case "无缓存":
            case "no":
                for (String out : LangManage.NOT_THIS_CODE) {
                    player.sendMessage(out);
                }
                return;
            case "yes":
                for (String out : LangManage.NOT_USER_YOU_CODE) {
                    player.sendMessage(out);
                }
        }
    }

    public void getRewardCheck(Player player) {
        String name = (player).getName();
        String uuid = String.valueOf((player).getUniqueId());
        int index = Invite_DataManage.getOwnerMap.size();
        if (Invite_DataManage.getOwnerMap.isEmpty()) {
            return;
        }
        for (int i = 1; i <= index; i++) {
            Invite_DataManage.InviteOwnerObj MapData = Invite_DataManage.getOwnerMap.get(i);
            if (MapData.getOWNER_NAME().equals(name) && MapData.getOWNER_UUID().equals(uuid)) {
                String owner_Name = MapData.getOWNER_NAME();
                String owner_Uuid = MapData.getOWNER_UUID();
                String owner_Cdk = MapData.getOWNER_CDK();
                int owner_Count = MapData.getOWNER_COUNT();
                REWARD = MapData.getOWNER_REWARD();
                //给予奖励
                GiveReward(player,name);
                //更新本地缓存
                Invite_DataManage.getOwnerMap.put(i, new Invite_DataManage.InviteOwnerObj(owner_Name, owner_Uuid, owner_Cdk, owner_Count, Owner_Reward));
                //更新本地变量缓存
                papiDataHead.setInviteOwnerMap(owner_Name, owner_Uuid, owner_Cdk, owner_Count, Owner_Reward);
                //更新数据库信息
                Invite_DataManage.setOwnerData(player,name,Owner_Reward);
                return;
            }
        }
    }

    /**
     *  给予使用者奖励
     * @param player 目标玩家
     * @param User_name 玩家名称
     */
    public void getUserReward(Player player, String User_name) {
        String uuid = String.valueOf(Objects.requireNonNull(player.getPlayer()).getUniqueId());
        String u = Joiner.on(",").join(ConfigManage.USER_REWARD).replace("[player_name]",User_name).replace("[player_uuid]",uuid);
        String[] Run = u.split(",");
        for (String run : Run) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), run);
        }
    }
    /**
     * 查询本地数据缓存，检查玩家是否兑换过这个邀请码，并且检查是不是自己的邀请码
     * @param name 玩家名字
     * @param code 玩家输入的CDK
     * @return 是玩家自己的邀请码则返回 false
     * 如果缓存中存在这个邀请码，并且不是自己的 则返回 true
     *
     * 这是邀请码 拥有者 的缓存 map
     *
     */
    public boolean isExist(String name, String code) {
        if (Invite_DataManage.getOwnerMap.isEmpty()) {
            IsMe = "无缓存";
            return false;
        }
        int index = Invite_DataManage.getOwnerMap.size();
        for (int i = 1; i <= index; i++) {
            Invite_DataManage.InviteOwnerObj MapData = Invite_DataManage.getOwnerMap.get(i);
            if (MapData.getOWNER_NAME().equals(name) && MapData.getOWNER_CDK().equals(code)) {
                IsMe = "yes";
                return false;
            }
            if (MapData.getOWNER_CDK().equals(code)) {
                OWNER_name = MapData.getOWNER_NAME();
                OWNER_uuid = MapData.getOWNER_UUID();
                OWNER_count = (MapData.getOWNER_COUNT() + 1);
                OWNER_reward = (MapData.getOWNER_REWARD() + 1);
                INDEX = i;
                return true;
            }
        }
        IsMe = "no";
        return false;
    }

    /**
     * 获取邀请码拥有着剩余的奖励次数
     * @param name
     * @return
     */
    public int getOwner_Reward(String name) {
        int index = Invite_DataManage.getOwnerMap.size();

        for (int i = 1; i <= index; i++) {
            Invite_DataManage.InviteOwnerObj MapData = Invite_DataManage.getOwnerMap.get(i);
            if (MapData.getOWNER_NAME().equals(name)) {
                return MapData.getOWNER_REWARD();
            }
        }
        return 0;
    }


    /**
     * 检查使用者本地缓存，查看本地是否有对应数据，如果有则代表该邀请码已被这个玩家使用，返回true
     * @param name 玩家名
     * @param code 输入的cdk
     * @return 返回布尔值
     *
     * 这是邀请码 使用者 的缓存map
     *
     */
    public boolean isUsed(String name, String code, String ip) {
        if (Invite_DataManage.getUserMap.isEmpty()) {
            return false;
        }
        int index = Invite_DataManage.getUserMap.size();
        for (int i = 1; i <= index; i++) {
            Invite_DataManage.InviteUserObj MapData = Invite_DataManage.getUserMap.get(i);
          /*  if (MapData.USER_IP.equals(ip) && MapData.USER_NAME.equals(name)) {
                IsIp = "yes";
                return false;
            }*/
            if (MapData.getUSER_NAME().equals(name) && ConfigManage.LIMIT_USE) {
                return true;
            }
            if (MapData.getUSER_NAME().equals(name) && MapData.getUSER_CDK().equals(code) ) {
                return true;
            }
        }
        return false;
    }


    /**
     * 玩家购买（获取） 邀请码
     * @param p 玩家对象
     * @param name 玩家名称
     * @param uuid 玩家uuid
     */
    public void BuyCdk(Player p, String name, String uuid) {
        if (isNoBuy(name)) {
            TextComponent text = new TextComponent("    §7[§A点击复制§7]");
            text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, Owner_cdk));
            for(String out : LangManage.ME_CODE)
            {
                p.sendMessage(out.replace("[cdk]", Owner_cdk));
            }
            p.spigot().sendMessage(text);
            p.sendMessage("");
            p.sendMessage("");
            return;
        }
        int in = (Invite_DataManage.getOwnerMap.size()+1);
        Invite_DataManage.setOwnerNewData(p, name, uuid, in);
    }


    /**
     * 判断本地缓存是否有该玩家的数据，如果没有则返回 false
     * 如果本地缓存数据集合全部为空，也返回 false
     * @param name 玩家名字
     * @return 返回结果
     */
    public boolean isNoBuy(String name) {
        if (Invite_DataManage.getOwnerMap.isEmpty()) {
            return false;
        }
        int index = Invite_DataManage.getOwnerMap.size();
        for (int i = 1; i <= index; i++) {
            Invite_DataManage.InviteOwnerObj MapData = Invite_DataManage.getOwnerMap.get(i);

            if (MapData.getOWNER_NAME().equals(name)) {
                Owner_cdk = MapData.getOWNER_CDK();
                return true;
            }
        }
        return false;
    }


    /**
     *
     * @param player 目标玩家
     * @param Owner_name 玩家名称
     *
     */
    private void GiveReward(Player player, String Owner_name) {
        String uuid = String.valueOf(player.getPlayer().getUniqueId());
        String u = Joiner.on(",").join(ConfigManage.OWNER_REWARD).replace("[player_name]",Owner_name).replace("[player_uuid]",uuid);
        String[] Run = u.split(",");
        for (int i = 0; i < REWARD; i++) {
            for (String run : Run) {
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), run);
            }
        }
    }

}
