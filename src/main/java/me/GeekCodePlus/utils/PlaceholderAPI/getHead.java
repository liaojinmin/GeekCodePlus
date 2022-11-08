package me.GeekCodePlus.utils.PlaceholderAPI;

public class getHead {
    /*
        获取名字
     */
    public static String getHeadName(int i) {
        papiOwnerTopObj resHead = papiDataHead.getInviteTop.get(i);
        if (resHead == null) {
            return "null";
        }
        return resHead.O_NAME;
    }

    /*
        获取数量
     */
    public static String getHeadCount(int i) {
        papiOwnerTopObj resHead = papiDataHead.getInviteTop.get(i);
        if (resHead == null) {
            return "null";
        }
        return String.valueOf(resHead.O_COUNT);
    }
}
