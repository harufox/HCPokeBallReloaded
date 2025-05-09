package jp.haruserver.mc.hcpokeball.util;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class ItemStackSerializer {

    public static String toBase64Array(ItemStack[] items) {
        YamlConfiguration config = new YamlConfiguration();
        config.set("items", items);
        String yamlString = config.saveToString();
        return Base64.getEncoder().encodeToString(yamlString.getBytes(StandardCharsets.UTF_8));
    }

    public static ItemStack[] fromBase64Array(String base64) {
        byte[] data = Base64.getDecoder().decode(base64);
        String yamlString = new String(data, StandardCharsets.UTF_8);
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.loadFromString(yamlString);
        } catch (Exception e) {
            throw new RuntimeException("デシリアライズ失敗", e);
        }
        @SuppressWarnings("unchecked")
        List<ItemStack> itemList = (List<ItemStack>) config.getList("items");
        return itemList != null ? itemList.toArray(new ItemStack[0]) : new ItemStack[0];
    }

    public static String toBase64(ItemStack item) {
        YamlConfiguration config = new YamlConfiguration();
        config.set("item", item);
        String yamlString = config.saveToString();
        return Base64.getEncoder().encodeToString(yamlString.getBytes(StandardCharsets.UTF_8));
    }

    public static ItemStack fromBase64(String base64) {
        byte[] data = Base64.getDecoder().decode(base64);
        String yamlString = new String(data, StandardCharsets.UTF_8);
        YamlConfiguration config = new YamlConfiguration();
        try {
            config.loadFromString(yamlString);
        } catch (Exception e) {
            throw new RuntimeException("デシリアライズ失敗", e);
        }
        return ((ItemStack) config.get("item"));
    }
}
