package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.event.Listener;

import org.bukkit.Material;
import org.bukkit.entity.Egg;
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

        //卵を使う以外で卵が飛んだ想定
        if (playerHand == null) return;
        Material playerHandType = playerHand.getType();
    
        //卵以外の何かを使って卵が飛んだ想定
        if(!playerHandType.equals(Material.EGG)) return;
        Egg egg = e.getEgg();
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        //卵にownerUUIDがあるかチェック
        if(!pokeBallKeys.hasOwnerUUID(playerHand)) return;

        String ownerUUID = pokeBallKeys.getOwnerUUID(playerHand);
        pokeBallKeys.setProjectileOwnerUUID(egg, ownerUUID);

    }
}
