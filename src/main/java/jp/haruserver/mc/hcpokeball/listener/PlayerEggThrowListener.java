package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.event.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class PlayerEggThrowListener implements Listener {

    private final HCPokeBall plugin;

    public PlayerEggThrowListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onEggThrow(PlayerEggThrowEvent e) {
        Player player = e.getPlayer();
        player.sendMessage(Component.text("卵投げ発火",NamedTextColor.GRAY));
        ItemStack playerHand = player.getInventory().getItemInMainHand();

        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        //卵にownerUUIDがあるかチェック
        if(!pokeBallKeys.hasOwnerUUID(playerHand)) return;

        player.sendMessage(Component.text("ひよこ抑制",NamedTextColor.GRAY));
        e.setHatching(false); // ひよこが出ないようにする
        e.setNumHatches((byte) 0); // 安全対策


    }
}
