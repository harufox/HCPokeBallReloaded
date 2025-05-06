package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PlayerProjectileLaunchListener implements Listener{

    private final HCPokeBall plugin;

    public PlayerProjectileLaunchListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onProjectileLaunch(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        // 卵（Egg）のみ対象
        if (!(projectile instanceof Egg)) return;

        // プレイヤーが投げたかチェック
        if (!(projectile.getShooter() instanceof Player)) return;

        Player player = (Player) projectile.getShooter();
        ItemStack playerHand = player.getInventory().getItemInMainHand();

        //卵を使う以外で卵が飛んだ想定
        if (playerHand == null) return;
        Material playerHandType = playerHand.getType();
        //卵以外の何かを使って卵が飛んだ想定
        if(!playerHandType.equals(Material.EGG)) return;

        Egg egg = (Egg)projectile;
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        // NBTにオーナーUUIDを埋め込む
        String ownerUUID = pokeBallKeys.getOwnerUUID(playerHand);
        pokeBallKeys.setProjectileOwnerUUID(egg, ownerUUID);

    }
}
