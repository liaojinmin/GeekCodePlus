package me.GeekCodePlus.utils.PlaceholderAPI;

import me.GeekCodePlus.GeekCodeMain;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;


public class papi_Hook extends PlaceholderExpansion {

    public papi_Hook(GeekCodeMain plugin) {
    }

    public String onPlaceholderRequest(Player p, String s) {
        String name = p.getName();
        if (s.equalsIgnoreCase("i_owner_name")){
            if (!papiDataHead.getOwnerData.isEmpty()) {
                papiOwnerObj res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return res.O_NAME;
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_uuid")){
            if (!papiDataHead.getOwnerData.isEmpty()) {
                papiOwnerObj res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return res.O_UUID;
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_cdk")){
            if (!papiDataHead.getOwnerData.isEmpty()) {
                papiOwnerObj res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return res.O_CDK;
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_count")){
            if (!papiDataHead.getOwnerData.isEmpty()) {
                papiOwnerObj res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return String.valueOf(res.O_COUNT);
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_reward")){
            if (!papiDataHead.getOwnerData.isEmpty()) {
                papiOwnerObj res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return String.valueOf(res.O_REWARD);
            }
            return "null";
        }
        /*
        注册邀请码排行榜
         */
        if (s.equalsIgnoreCase("i_1_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(1);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_2_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(2);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_3_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(3);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_4_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(4);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_5_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(5);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_6_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(6);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_7_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(7);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_8_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(8);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_9_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(9);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_10_name")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(10);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        /*
        count
         */
        if (s.equalsIgnoreCase("i_1_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(1);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_2_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(2);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_3_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(3);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_4_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(4);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_5_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(5);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_6_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(6);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_7_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(7);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_8_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(8);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_9_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(9);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_10_count")){
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(10);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }

        return "null";
    }


    public String getIdentifier() { return "geekc"; }
    public String getAuthor() { return "GeekCraft"; }
    public String getVersion() { return GeekCodeMain.Version; }
    public boolean persist() { return true; }
}
