package me.GeekCodePlus.utils.PlaceholderAPI;

public class papiOwnerObj {

    public String O_NAME;
    public String O_UUID;
    public String O_CDK;
    public int O_COUNT;
    public int O_REWARD;
    /**
     * @param name 玩家名称
     * @param uuid 玩家uuid
     * @param cdk cdk
     * @param count 被使用次数
     * @param reward 剩余的奖励次数
     */
    public papiOwnerObj(String name, String uuid, String cdk, int count, int reward) {
        this.O_NAME = name;
        this.O_UUID = uuid;
        this.O_CDK = cdk;
        this.O_COUNT = count;
        this.O_REWARD = reward;
    }


}
