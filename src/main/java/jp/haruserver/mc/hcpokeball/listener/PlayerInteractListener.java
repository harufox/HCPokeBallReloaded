package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PlayerInteractListener implements Listener{


    private final HCPokeBall plugin;

    public PlayerInteractListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerEggUseUse(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        ItemStack item = e.getItem();
    
        //spawneggかチェック
        if (item == null || !item.getType().name().endsWith("_SPAWN_EGG")) return;

        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
        //OwnerUUIDがあるかチェック
        if(!pokeBallKeys.hasOwnerUUID(item)) return;
        // イベントキャンセルしてスポーン阻止
        e.setCancelled(true);
        
        //使用者UUIDとオーナーUUIDの一致確認
        if(!pokeBallKeys.getOwnerUUID(item).equals(player.getUniqueId().toString())) return;
    
        // NBT読み取り
        String NBTString = pokeBallKeys.getNbtString(item);
    
        // アイテムを1つ減らす
        if (player.getGameMode() != GameMode.CREATIVE) {
            item.setAmount(item.getAmount() - 1);
        }
    
        // 卵を投げる（方向調整付き）
		Location loc = player.getLocation();
		Vector vec = loc.getDirection();
		Egg egg = player.launchProjectile(Egg.class,vec);
        egg.setShooter(player);
        pokeBallKeys.setProjectileOwnerUUID(egg,player.getUniqueId().toString());
        pokeBallKeys.setProjectileNbtString(egg, NBTString);
   
	}
}
