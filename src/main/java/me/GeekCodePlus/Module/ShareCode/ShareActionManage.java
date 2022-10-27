package me.GeekCodePlus.Module.ShareCode;

import com.google.common.base.Joiner;
import me.GeekCodePlus.Configure.ConfigManage;
import me.GeekCodePlus.utils.StreamSerializer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public final class ShareActionManage {

    /**
     * 检查玩家主手物品，无则返回 false
     * @param player 玩家对象
     * @return 返回布尔值
     */
    public boolean isItem(Player player) {
        try {
            if (player.getInventory().getItemInMainHand().getType().name().equalsIgnoreCase("AIR")) {
                return false;
            }
        } catch (NoSuchMethodError e) {
            if (player.getInventory().getItemInHand().getType().name().equalsIgnoreCase("AIR")) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取玩家手持物品的物品堆伐
     * @param p 玩家对象
     * @return 返回玩家手持的 ItemStack 物品堆伐
     */
    public ItemStack getItemInHand(Player p) {
        try {
            ItemStack a = p.getInventory().getItemInMainHand();
            p.getInventory().remove(a);
            return a;
        } catch (NoSuchMethodError e) {
            ItemStack a = p.getInventory().getItemInHand();
            p.getInventory().remove(a);
            return a;
        }
    }

    /**
     * 获取玩家手持物品的物品名称
     * @param p 玩家对象
     * @return 返回物品名称
     */
    public String getItemInHandName(Player p) {
        try {
            ItemStack a = p.getInventory().getItemInMainHand();
            if (a.getItemMeta() == null) {
                return a.getType().name();
            }
            return a.getItemMeta().getDisplayName();
        } catch (NoSuchMethodError e) {
            ItemStack a = p.getInventory().getItemInHand();
            if (a.getItemMeta() == null) {
                return a.getType().name();
            }
            return a.getItemMeta().getDisplayName();
        }
    }

    private ItemStack getDisplay(String Key) {
        Share_DataManage.ShareObj data = Share_DataManage.getCodeMap.get(Key);
        ItemStack A = null;
        try {
            A = StreamSerializer.deserializeInventory(data.getITEM_STACK());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (A != null) {
            ItemMeta out = A.getItemMeta();
            if (out.getLore() != null) {
                List<String> lore = out.getLore();
                lore.addAll(replace(ConfigManage.ICON_MAIN_LORE, data.getUPLOAD_PLAYNAME(), data.getUPLOAD_TIME(), data.getUSE_STATUS()));
                out.setLore(lore);
            } else {
                out.setLore(replace(ConfigManage.ICON_MAIN_LORE, data.getUPLOAD_PLAYNAME(), data.getUPLOAD_TIME(), data.getUSE_STATUS()));
            }
            A.setItemMeta(out);
        }
        return A;
    }
    public Inventory getGui(Player player, int size, String Key) {
        Inventory gui = Bukkit.createInventory(player, size, ConfigManage.GUI_NAME);

        ItemStack a = new ItemStack(Material.getMaterial(ConfigManage.ICON_OK_MATERIAL));
        ItemMeta OK = a.getItemMeta();
        OK.setDisplayName(ConfigManage.ICON_OK_NAME);
        a.setItemMeta(OK);

        ItemStack b = new ItemStack(Material.getMaterial(ConfigManage.ICON_CAN_MATERIAL));
        ItemMeta CAN = b.getItemMeta();
        CAN.setDisplayName(ConfigManage.ICON_CAN_NAME);
        b.setItemMeta(CAN);

        gui.setItem(11, a);
        gui.setItem(15, b);
        gui.setItem(4, getDisplay(Key));
        return gui;
    }

    private List<String> replace(List<String> s, String owner, String time, String status) {
        return Arrays.asList(Joiner.on(",").join(s)
                .replace("[owner]", owner)
                .replace("[time]", time)
                .replace("[status]", status)
                .split(","));
    }
}
