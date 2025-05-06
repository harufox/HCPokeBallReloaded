package jp.haruserver.mc.hcpokeball.listener;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Tameable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.registry.CaptureHandlerRegistry;
import jp.haruserver.mc.hcpokeball.util.ItemManager;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;
import jp.haruserver.mc.hcpokeball.util.mapper.EggMaterialMapper;
import net.md_5.bungee.api.ChatColor;

public class ProjectileHitListener implements Listener {

    private final HCPokeBall plugin;

    public ProjectileHitListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPokeBallHit(ProjectileHitEvent e) {
        // プレイヤーが投げた弾か判定
        if (!(e.getEntity().getShooter() instanceof Player)) return;
        // 卵型の弾（ポケボール）であるか判定
        if (!(e.getEntity() instanceof Egg)) return;

        Player player = (Player) e.getEntity().getShooter();
        Egg egg = (Egg) e.getEntity();
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        // 発射体にオーナーUUIDがあるか確認（安全対策）
        if (!pokeBallKeys.hasProjectileOwnerUUID(egg)) return;

        // UUIDが本人と一致するか（他人のボールによる干渉を防ぐ）
        String playerUUID = player.getUniqueId().toString();
        String projectileOwnerUUID = pokeBallKeys.getProjectileOwnerUUID(egg);
        if (!playerUUID.equals(projectileOwnerUUID)) return;

        // 捕獲済みかどうかで分岐（NBTにjsonが入っているか）
        if (pokeBallKeys.hasProjectileNbtString(egg)) {
            pokeBallRelease(player, egg);
        } else {
            pokeBallCapture(player, egg, e.getHitEntity());
        }
    }

    /**
     * 捕獲処理：空のポケボールがペットMOBに当たった時に呼ばれる
     */
    private void pokeBallCapture(Player player, Egg egg, Entity hitEntity) {
        ItemManager itemManager = plugin.getItemManager();

        // 外れた場合（何にも当たらなかった）
        if (hitEntity == null) {
            player.sendMessage(ChatColor.AQUA + "ボールははずれた・・・");
            dropPokeBall(player);
            return;
        }

        // Tameable（ペット化可能）でないと捕獲不可
        if (!(hitEntity instanceof Tameable)) {
            player.sendMessage(ChatColor.AQUA + "捕まえられないようだ・・・");
            dropPokeBall(player);
            return;
        }

        // ホワイトリストによる制限（configで定義）
        EntityType type = hitEntity.getType();
        List<String> whitelist = plugin.getConfigMapList().get("captureWhitelist");
        if (whitelist.stream().noneMatch(name -> name.equalsIgnoreCase(type.name()))) {
            player.sendMessage(ChatColor.AQUA + "捕まえられないようだ・・・");
            dropPokeBall(player);
            return;
        }

        // シリアライズ可能なハンドラーが登録されているか
        if (!CaptureHandlerRegistry.isSupported(type)) {
            player.sendMessage(ChatColor.AQUA + "そのモブはまだ捕獲できないようだ…");
            dropPokeBall(player);
            return;
        }

        // エンティティをJSON化して保存
        EntityCaptureHandler handler = CaptureHandlerRegistry.getHandler(type);
		@SuppressWarnings("unchecked")
        String json = handler.serialize(hitEntity);
        String petName = hitEntity.getName();  // カスタム名が設定されていれば使う
        String playerDisplayName = player.getName();
		String playerUUID = player.getUniqueId().toString();
		String entityTypeString = hitEntity.getType().name();
        Material eggMaterial = EggMaterialMapper.getEggMaterial(type);

        // 捕獲済みポケボールを生成し、地面にドロップ
        ItemStack pokeball = itemManager.createCapturedPokeBall(petName, playerDisplayName, playerUUID,entityTypeString, json, eggMaterial);
        player.getWorld().dropItem(player.getLocation(), pokeball);

        // 元のエンティティを削除
        hitEntity.remove();
    }

	/**
	 * リリース処理：捕獲済みポケボールが命中した時に呼ばれる
	 */
	private void pokeBallRelease(Player player, Egg egg) {
		PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
		String json = pokeBallKeys.getProjectileNbtString(egg);
		String entityTypeName = pokeBallKeys.getProjectileEntityType(egg); // "WOLF"など
		EntityType entityType;

		try {
			entityType = EntityType.valueOf(entityTypeName);
		} catch (IllegalArgumentException ex) {
			player.sendMessage(ChatColor.RED + "不明なエンティティタイプです。");
			return;
		}

		// 登録済みのハンドラーがあるかチェック
		if (!CaptureHandlerRegistry.isSupported(entityType)) {
			player.sendMessage(ChatColor.RED + "このモブはリリースできません。");
			return;
		}

		EntityCaptureHandler<?> handler = CaptureHandlerRegistry.getHandler(entityType);

		// デシリアライズ → EntityData を得る
		EntityData entityData = handler.deserialize(json);
		if (entityData == null) {
			player.sendMessage(ChatColor.RED + "データの復元に失敗しました。");
			return;
		}

		// プレイヤーの少し前にスポーン（向きに応じて位置調整しても良い）
		Location spawnLoc = player.getLocation().add(0, 0.5, 0);

		// エンティティをスポーン
		Entity spawnedEntity = spawnLoc.getWorld().spawn(spawnLoc, entityType.getEntityClass());

		// データ適用
		if (entityData instanceof Tameable && spawnedEntity instanceof Tameable) {
			((Tameable) spawnedEntity).setOwner(player);
		}

		entityData.applyTo(spawnedEntity);

		player.sendMessage(ChatColor.GREEN + entityData.getType() + " を呼び出した！");
	}

    /**
     * 捕獲失敗時などに空のポケボールを返却する共通処理
     */
    private void dropPokeBall(Player player) {
        ItemManager itemManager = plugin.getItemManager();
        ItemStack emptyBall = itemManager.createPokeBall(player.getUniqueId().toString());
        player.getWorld().dropItem(player.getLocation(), emptyBall);
    }
}
