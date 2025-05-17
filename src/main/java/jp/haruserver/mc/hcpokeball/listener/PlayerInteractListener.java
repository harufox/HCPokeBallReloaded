package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
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
import jp.haruserver.mc.hcpokeball.util.MessageManager;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;



public class PlayerInteractListener implements Listener{


    private final HCPokeBall plugin;

    public PlayerInteractListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

	@EventHandler(priority = EventPriority.HIGH)
	public void onPlayerEggUseUse(PlayerInteractEvent event) {
        Player player = event.getPlayer();

        ItemStack item = event.getItem();
    
        //何も持っていないかチェック
        if (item == null) return;
        
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
        //OwnerUUIDがあるかチェック
        if(!pokeBallKeys.hasOwnerUUID(item)) return;
        
        //卵ではない場合イベントキャンセル
        if(!(item.getType().equals(Material.EGG))){
            event.setCancelled(true);
        }

        if (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        String playerUUID = player.getUniqueId().toString();
        String ownerUUID = pokeBallKeys.getOwnerUUID(item);
        MessageManager messageManager = plugin.getMessageManager();
        String messagePrefix = messageManager.getMessage(player, "pokeball.prefix");
        //使用者UUIDとオーナーUUIDの一致確認
        if(!ownerUUID.equals(playerUUID)){
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "pokeball.notyourball"));
            event.setCancelled(true);
            return;
        }

        //持ち物判定
        Material itemType = item.getType();
        //スポーンエッグの場合
        if (itemType.name().endsWith("_SPAWN_EGG")){
            playerThrowSpawnEgg(player, item,event);
            return;
        }
        return;
    }

    private void playerThrowSpawnEgg(Player player,ItemStack item,PlayerInteractEvent event){
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
    
        // 卵を投げる（方向調整付き）
		Location loc = player.getLocation();
		Vector vec = loc.getDirection();
		Egg egg = player.launchProjectile(Egg.class,vec);
        egg.setShooter(player);
        String playerUUID = player.getUniqueId().toString();
        // NBT読み取り
        String NBTString = pokeBallKeys.getNbtString(item);
        String entityTypeString = pokeBallKeys.getEntityType(item);
        pokeBallKeys.setProjectileOwnerUUID(egg,playerUUID);
        pokeBallKeys.setProjectileNbtString(egg, NBTString);
        pokeBallKeys.setProjectileEntityType(egg, entityTypeString);

        Bukkit.getServer().getLogger().info("ペット召喚:" + playerUUID + "|" + NBTString + "|" + entityTypeString);

        // アイテムを1つ減らす
        if (player.getGameMode() != GameMode.CREATIVE) {
            item.setAmount(item.getAmount() - 1);
        }
    }
}
