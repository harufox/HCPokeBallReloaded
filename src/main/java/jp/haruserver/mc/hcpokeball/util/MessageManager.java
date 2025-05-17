package jp.haruserver.mc.hcpokeball.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class MessageManager {
    private final JavaPlugin plugin;
    private final Map<String, FileConfiguration> messages = new HashMap<>();
    private final String defaultLanguage = "en";

    public MessageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        loadMessages();
    }

    private void loadMessages() {
        loadLocale("en", "messages_en.yml");
        loadLocale("ja", "messages_ja.yml");
    }

    private void loadLocale(String langCode, String fileName) {
        File file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        messages.put(langCode, config);
    }

    public String getMessage(Player player, String key) {
        String lang = getPlayerLanguage(player);
        return getMessage(lang, key);
    }

    public String getMessage(String lang, String key) {
        FileConfiguration config = messages.getOrDefault(lang, messages.get(defaultLanguage));
        String raw = config.getString(key, "!" + key + "!");
        return ChatColor.translateAlternateColorCodes('&', raw);
    }

    public String getMessage(Player player, String key, Map<String, String> placeholders) {
        String msg = getMessage(player, key);
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            msg = msg.replace("%" + entry.getKey() + "%", entry.getValue());
        }
        return ChatColor.translateAlternateColorCodes('&', msg);
    }

    public String getMessage(Player player, String key, String... keyValuePairs) {
        Map<String, String> placeholders = new HashMap<>();
        for (int i = 0; i + 1 < keyValuePairs.length; i += 2) {
            placeholders.put(keyValuePairs[i], keyValuePairs[i + 1]);
        }
        return getMessage(player, key, placeholders);
    }

    private String getPlayerLanguage(Player player) {
        try {
            return player.locale().getLanguage(); // 例: "ja"
        } catch (Exception e) {
            return defaultLanguage;
        }
    }
}