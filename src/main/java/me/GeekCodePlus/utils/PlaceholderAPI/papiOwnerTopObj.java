package me.GeekCodePlus.utils.PlaceholderAPI;

public class papiOwnerTopObj {

    public String O_NAME;
    public int O_COUNT;
    /**
     * @param name 玩家名称
     * @param count 被使用次数
     */
    public papiOwnerTopObj(String name, int count) {
        this.O_NAME = name;
        this.O_COUNT = count;
    }

    public void setO_COUNT(int O_COUNT) {
        this.O_COUNT = O_COUNT;
    }

    public void setO_NAME(String O_NAME) {
        this.O_NAME = O_NAME;
    }
}
