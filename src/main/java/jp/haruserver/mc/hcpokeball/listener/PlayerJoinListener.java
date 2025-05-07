package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import jp.haruserver.mc.hcpokeball.HCPokeBall;

public class PlayerJoinListener implements Listener{

    private final HCPokeBall plugin;

    public PlayerJoinListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    //プレイヤー接続時にレシピ登録する
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.discoverRecipe(new NamespacedKey(plugin, "pokeball"));
    }
}
