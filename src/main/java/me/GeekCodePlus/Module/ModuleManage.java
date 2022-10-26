package me.GeekCodePlus.Module;

import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.Module.ActivationCode.ActivationActionManage;
import me.GeekCodePlus.Module.ActivationCode.Activation_DataManage;
import me.GeekCodePlus.Module.InviteCode.InviteActionManage;
import me.GeekCodePlus.Module.InviteCode.Invite_DataManage;
import me.GeekCodePlus.Module.ShareCode.ShareActionManage;
import me.GeekCodePlus.Module.ShareCode.Share_DataManage;
import me.GeekCodePlus.utils.PlaceholderAPI.papiDataHead;

public final class ModuleManage {

    public ModuleManage() {
        onstart();
    }

    private void onstart() {
        if (ConfigManage.USER_INVITE_CODE) {
            Invite_DataManage.INVITE_getData();
            Invite_DataManage.INVITE_getUserData();
            papiDataHead.getInviteTop();
            GeekCodeMain.inviteActionManage = new InviteActionManage();
            GeekCodeMain.say("§6邀请码 §7模块§8-§a启用");
        } else {
            GeekCodeMain.say("§6邀请码 §7模块§8-§c禁用");
        }
        if (ConfigManage.USER_ACTIVATION_CODE) {
            Activation_DataManage.ACTIVATION_getData();
            Activation_DataManage.ACTIVATION_getUserData();
            GeekCodeMain.activationActionManage = new ActivationActionManage();
            GeekCodeMain.say("§6激活码 §7模块§8-§a启用");
        } else {
            GeekCodeMain.say("§6激活码 §7模块§8-§c禁用");
        }
        if (ConfigManage.USER_SHARE_CODE) {
            Share_DataManage.getShareData();
            GeekCodeMain.shareActionManage = new ShareActionManage();
            GeekCodeMain.say("§6分享码 §7模块§8-§a启用");
        } else {
            GeekCodeMain.say("§6分享码 §7模块§8-§c禁用");
        }
    }
}
