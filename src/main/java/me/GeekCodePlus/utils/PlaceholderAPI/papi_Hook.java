package me.GeekCodePlus.utils.PlaceholderAPI;

import me.GeekCodePlus.GeekCodeMain;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class papi_Hook extends PlaceholderExpansion {

    public papi_Hook(GeekCodeMain plugins) {
    }

    public String onPlaceholderRequest(Player p, String s) {
        String name = p.getName();
        papiOwnerObj res;
        if (s.equalsIgnoreCase("i_owner_name")) {
            if (!papiDataHead.getOwnerData.isEmpty()) {
                res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return res.O_NAME;
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_uuid")) {
            if (!papiDataHead.getOwnerData.isEmpty()) {
                res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return res.O_UUID;
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_cdk")) {
            if (!papiDataHead.getOwnerData.isEmpty()) {
                res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return res.O_CDK;
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_count")) {
            if (!papiDataHead.getOwnerData.isEmpty()) {
                res = papiDataHead.getOwnerData.get(name);
                if (res == null) {
                    return "null";
                }
                return String.valueOf(res.O_COUNT);
            }
            return "null";
        }
        if (s.equalsIgnoreCase("i_owner_reward")) {
            if (!papiDataHead.getOwnerData.isEmpty()) {
                res = papiDataHead.getOwnerData.get(name);
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
        switch (s) {
            case "i_1_name":
                return getHeadName(1);
            case "i_2_name":
                return getHeadName(2);
            case "i_3_name":
                return getHeadName(3);
            case "i_4_name":
                return getHeadName(4);
            case "i_5_name":
                return getHeadName(5);
            case "i_6_name":
                return getHeadName(6);
            case "i_7_name":
                return getHeadName(7);
            case "i_8_name":
                return getHeadName(8);
            case "i_9_name":
                return getHeadName(9);
            case "i_10_name":
                return getHeadName(10);
        }
        /*if (s.equalsIgnoreCase("i_1_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(1);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_2_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(2);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_3_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(3);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_4_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(4);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_5_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(5);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_6_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(6);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_7_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(7);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_8_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(8);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_9_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(9);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }
        if (s.equalsIgnoreCase("i_10_name")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(10);
            if (res == null) {
                return "null";
            }
            return res.O_NAME;
        }*/
        /*
        count
         */
        switch (s) {
            case "i_1_count":
                return getHeadCount(1);
            case "i_2_count":
                return getHeadCount(2);
            case "i_3_count":
                return getHeadCount(3);
            case "i_4_count":
                return getHeadCount(4);
            case "i_5_count":
                return getHeadCount(5);
            case "i_6_count":
                return getHeadCount(6);
            case "i_7_count":
                return getHeadCount(7);
            case "i_8_count":
                return getHeadCount(8);
            case "i_9_count":
                return getHeadCount(9);
            case "i_10_count":
                return getHeadCount(10);

        }
        /*
        if (s.equalsIgnoreCase("i_1_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(1);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_2_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(2);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_3_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(3);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_4_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(4);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_5_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(5);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_6_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(6);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_7_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(7);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_8_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(8);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_9_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(9);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
        if (s.equalsIgnoreCase("i_10_count")) {
            papiOwnerTopObj res = papiDataHead.getInviteTop.get(10);
            if (res == null) {
                return "null";
            }
            return String.valueOf(res.O_COUNT);
        }
*/
        return "null";
    }

    private String getHeadName(int i) {
        papiOwnerTopObj resHead = papiDataHead.getInviteTop.get(i);
        if (resHead == null) {
            return "null";
        }
        return resHead.O_NAME;
    }

    private String getHeadCount(int i) {
        papiOwnerTopObj resHead = papiDataHead.getInviteTop.get(i);
        if (resHead == null) {
            return "null";
        }
        return String.valueOf(resHead.O_COUNT);
    }

    public @NotNull String getIdentifier() {
        return "geekc";
    }

    public @NotNull String getAuthor() {
        return "GeekCraft";
    }

    public @NotNull String getVersion() {
        return GeekCodeMain.Version;
    }

    public boolean persist() {
        return true;
    }
}
