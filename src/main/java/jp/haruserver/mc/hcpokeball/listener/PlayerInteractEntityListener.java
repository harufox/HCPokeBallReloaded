package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PlayerInteractEntityListener implements Listener {

    private final HCPokeBall plugin;

    public PlayerInteractEntityListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack playerHand = player.getInventory().getItemInMainHand();

        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        //卵にownerUUIDがあるかチェック
        if(pokeBallKeys.hasOwnerUUID(playerHand)){
            event.setCancelled(true);
        }
        return;
    }

}