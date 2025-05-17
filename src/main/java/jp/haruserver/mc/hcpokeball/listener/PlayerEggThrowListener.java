package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.event.Listener;
import org.bukkit.entity.Egg;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEggThrowEvent;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PlayerEggThrowListener implements Listener {

    private final HCPokeBall plugin;

    public PlayerEggThrowListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent e) {

        Egg egg = e.getEgg();
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        //卵にownerUUIDがあるかチェック
        if(!pokeBallKeys.hasProjectileOwnerUUID(egg)) return;

        e.setHatching(false); // ひよこが出ないようにする
        e.setNumHatches((byte) 0); // 安全対策
    }
}
