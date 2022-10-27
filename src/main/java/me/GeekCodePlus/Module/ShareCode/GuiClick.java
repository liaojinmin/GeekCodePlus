package me.GeekCodePlus.Module.ShareCode;

import me.GeekCodePlus.GeekCodeMain;
import me.GeekCodePlus.utils.StreamSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;

public final class GuiClick {
    public static void open(Player player, String Key) {
        Inventory inventory = GeekCodeMain.shareActionManage.getGui(player, 18, Key);
        player.closeInventory();
        player.openInventory(inventory);

        Bukkit.getPluginManager().registerEvents(new Listener() {

            /*
            @EventHandler
            public void onMove(InventoryMoveItemEvent e) {
                Material ok = Material.getMaterial(ConfigManager.MATERIAL_OK);
                Material can = Material.getMaterial(ConfigManager.MATERIAL_CAN);
                if (e.getItem().getType().equals(ok) || e.getItem().getType().equals(can)) {
                    e.setCancelled(true);
                }
            }*/
            @EventHandler
            public void onClick(InventoryClickEvent e) {
                Inventory inv = e.getInventory();
                e.setCancelled(true);
                if (inv.getType() == InventoryType.PLAYER || e.getRawSlot() < 0) {
                    return;
                }
                switch (e.getRawSlot()) {
                    case 11:
                        Share_DataManage.ShareObj data = Share_DataManage.getCodeMap.get(Key);
                        Share_DataManage.Update(data.getSERVER_NAME(),
                                data.getITEM_STACK(),
                                data.getITEM_NAME(),
                                data.getCODE(),
                                data.getUPLOAD_TIME(),
                                data.getUPLOAD_PLAYNAME(),
                                player.getName());
                        giveItem(player, data.getITEM_STACK());

                        //Share_DataManage.getMeCodeMap.remove(data.getUPLOAD_PLAYNAME());
                        Share_DataManage.getShareMeData(data.getUPLOAD_PLAYNAME());

                        player.closeInventory();
                        return;
                    case 15:
                        player.closeInventory();
                }
            }

            @EventHandler
            public void onDrag(InventoryDragEvent e) {
                Inventory inv = e.getInventory();
                if (player.equals(e.getWhoClicked()) && inv.equals(e.getInventory())) {
                    e.setCancelled(true);
                }
            }

            @EventHandler
            public void onClose(InventoryCloseEvent e) {
                Inventory inv = e.getInventory();
                player.updateInventory();
                if (player.equals(e.getPlayer()) && inv.equals(e.getInventory())) {
                    HandlerList.unregisterAll(this);
                }
            }
        },GeekCodeMain.instance);
    }
    private static void giveItem(Player player, String ItemKey) {
        ItemStack A = null;
        try {
            A = StreamSerializer.deserializeInventory(ItemKey);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (A != null) {
            player.getInventory().addItem(A);
        }
    }
}
