package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PlayerEggThrowListener implements Listener {

    private final HCPokeBall plugin;

    public PlayerEggThrowListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent e) {
        Player player = e.getPlayer();
        ItemStack playerHand = player.getInventory().getItemInMainHand();

        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        //卵にownerUUIDがあるかチェック
        if(!pokeBallKeys.hasOwnerUUID(playerHand)) return;

        e.setHatching(false); // ひよこが出ないようにする
        e.setNumHatches((byte) 0); // 安全対策


    }
}
