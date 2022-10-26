package me.GeekCodePlus.utils;

import org.bukkit.inventory.ItemStack;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

public class StreamSerializer {

    //序列化
    public static String serializeInventory(ItemStack inventoryContents) {
        ByteArrayOutputStream byteOutputStream = new ByteArrayOutputStream();
        try (BukkitObjectOutputStream bukkitOutputStream = new BukkitObjectOutputStream(byteOutputStream)) {
            bukkitOutputStream.writeObject(serializeItemStack(inventoryContents));
            return Base64Coder.encodeLines(byteOutputStream.toByteArray());
        } catch (IOException e) {
            throw new IllegalArgumentException("无法序列化物品堆栈数据");
        }
    }

    //反序列化
    public static ItemStack deserializeInventory(String inventoryData) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteInputStream = new ByteArrayInputStream(Base64Coder.decodeLines(inventoryData))) {
            try (BukkitObjectInputStream bukkitInputStream = new BukkitObjectInputStream(byteInputStream)) {
                return deserializeItemStack(bukkitInputStream.readObject());
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static ItemStack deserializeItemStack(Object serializedItemStack) {
        return serializedItemStack != null ? ItemStack.deserialize((Map<String, Object>) serializedItemStack) : null;
    }
    private static Map<String, Object> serializeItemStack(ItemStack item) {
        return item != null ? item.serialize() : null;
    }
}
