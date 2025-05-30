package jp.haruserver.mc.hcpokeball.listener;

import java.util.List;
import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;
import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.registry.CaptureConditionRegistry;
import jp.haruserver.mc.hcpokeball.registry.CaptureHandlerRegistry;
import jp.haruserver.mc.hcpokeball.util.ItemManager;
import jp.haruserver.mc.hcpokeball.util.MessageManager;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ProjectileHitListener implements Listener {

    private final HCPokeBall plugin;

    public ProjectileHitListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPokeBallHit(ProjectileHitEvent e) {
        MessageManager messageManager = plugin.getMessageManager();
        // プレイヤーが投げた弾か判定
        if (!(e.getEntity().getShooter() instanceof Player)) return;
        Player player = (Player) e.getEntity().getShooter();
        String messagePrefix = messageManager.getMessage(player, "pokeball.prefix");
        // 卵であるか判定
        if (!(e.getEntity() instanceof Egg)) return;
        Egg egg = (Egg) e.getEntity();
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();

        // 発射体にオーナーUUIDがあるか確認
        if (!pokeBallKeys.hasProjectileOwnerUUID(egg)) return;
        // UUIDが本人と一致するか（他人のボールによる干渉を防ぐ）
        String playerUUID = player.getUniqueId().toString();
        String projectileOwnerUUID = pokeBallKeys.getProjectileOwnerUUID(egg);
        if (!playerUUID.equals(projectileOwnerUUID)){
			player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.missed"));
			return;
		}

        // 捕獲済みかどうかで分岐（NBTにjsonが入っているか）
        if (pokeBallKeys.hasProjectileNbtString(egg)) {
            pokeBallRelease(player, egg);
        } else {
            pokeBallCapture(player, egg, e.getHitEntity());
        }
        return;
    }

    /**
     * 捕獲処理：空のポケボールがペットMOBに当たった時に呼ばれる
     */
    private void pokeBallCapture(Player player, Egg egg, Entity hitEntity) {
        MessageManager messageManager = plugin.getMessageManager();
        ItemManager itemManager = plugin.getItemManager();
        String messagePrefix = messageManager.getMessage(player, "pokeball.prefix");

        //外れた場合（何にも当たらなかった）
        if (hitEntity == null) {
            dropPokeBall(player);
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.missed"));
            return;
        }
        //人に当てた場合
        if (hitEntity instanceof Player) {
            dropPokeBall(player);
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.otherplayer"));
            return;
        }
		String playerUUID = player.getUniqueId().toString();

        //ホワイトリストによる制限（configで定義）
        EntityType type = hitEntity.getType();
        List<String> whitelist = plugin.getConfigMapList().get("captureWhitelist");
        if (whitelist.stream().noneMatch(name -> name.equalsIgnoreCase(type.name()))) {
            dropPokeBall(player);
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.notinwhitelist"));
            return;
        }

        //シリアライズ可能なハンドラーが登録されているか
        if (!CaptureHandlerRegistry.isSupported(type)) {
            dropPokeBall(player);
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.notauth"));
            return;
        }

        Optional<CaptureCondition> optionalCondition = CaptureConditionRegistry.getCondition(type);

        //Condition未登録の場合
        if (optionalCondition.isEmpty()) {
            dropPokeBall(player);
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.notauth"));
            return;
        }

        //捕獲判定
        CaptureCondition condition = optionalCondition.get();
        if (!condition.canCapture(hitEntity, player)) {
            dropPokeBall(player);
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.failed.donthavepermission"));
            return;
        }

        //エンティティをJSON化して保存
        EntityCaptureHandler handler = CaptureHandlerRegistry.getHandler(type);
        String json = handler.serialize(hitEntity);
        String petName = hitEntity.getName();  // カスタム名が設定されていれば使う
        String playerDisplayName = player.getName();
		String entityTypeString = hitEntity.getType().name();
        Material eggMaterial = handler.getEggMaterial();

        //捕獲済みポケボールを生成し、地面にドロップ
        ItemStack pokeball = itemManager.createCapturedPokeBall(petName, playerDisplayName, playerUUID,entityTypeString, json, eggMaterial);
        player.getWorld().dropItem(player.getLocation(), pokeball);

        World world = hitEntity.getWorld();
        world.playSound(hitEntity.getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, 0.8f, 1.2f);

        for (int i = 0; i < 5; i++) {
            Location loc = hitEntity.getLocation().clone().add(
                (Math.random() - 0.5) * 1.5,
                Math.random() * 2.0,
                (Math.random() - 0.5) * 1.5
            );
            world.spawnParticle(Particle.REVERSE_PORTAL, loc, 15, 0.2, 0.2, 0.2, 0.05);
            world.spawnParticle(Particle.END_ROD, loc, 10, 0.1, 0.1, 0.1, 0.01);
        }
        //元のエンティティを削除
        player.sendMessage(messagePrefix + messageManager.getMessage(player, "capture.success","pet",petName));
        hitEntity.remove();
    }

	/**
	 * リリース処理：捕獲済みポケボールが命中した時に呼ばれる
	 */
	private void pokeBallRelease(Player player, Egg egg) {
        MessageManager messageManager = plugin.getMessageManager();
        String messagePrefix = messageManager.getMessage(player, "pokeball.prefix");
		PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
		String json = pokeBallKeys.getProjectileNbtString(egg);
		String entityTypeName = pokeBallKeys.getProjectileEntityType(egg); // "WOLF"など
		EntityType entityType;

		try {
			entityType = EntityType.valueOf(entityTypeName);
		} catch (IllegalArgumentException ex) {
            player.sendMessage(messagePrefix + messageManager.getMessage(player, "release.failed.unknowntype"));
			return;
		}

		//登録済みのハンドラーがあるかチェック
		if (!CaptureHandlerRegistry.isSupported(entityType)) {
			player.sendMessage(messagePrefix + messageManager.getMessage(player, "release.failed.notauth"));
			return;
		}

		EntityCaptureHandler<?> handler = CaptureHandlerRegistry.getHandler(entityType);

		//デシリアライズ → EntityData を得る
		EntityData entityData = handler.deserialize(json);
		if (entityData == null) {
			player.sendMessage(messagePrefix + messageManager.getMessage(player, "release.failed.deserializeerror"));
			return;
		}

		//着弾地点にスポーンさせる
		Location spawnLoc = egg.getLocation().add(0, 0.5, 0);
		World spawnWorld = spawnLoc.getWorld();
		
		//エンティティをスポーン
		Entity spawnedEntity = spawnLoc.getWorld().spawn(spawnLoc, entityType.getEntityClass());

		entityData.applyTo(spawnedEntity,player);
        String petName = entityData.getType();
       
        if(spawnedEntity.customName() != null){
            petName = LegacyComponentSerializer.legacySection().serialize(spawnedEntity.customName());
        }

        dropPokeBall(player);
        player.sendMessage(messagePrefix + messageManager.getMessage(player, "release.success","pet",petName));
		spawnWorld.spawnParticle(Particle.EXPLOSION, spawnLoc, 10);
		spawnWorld.spawnParticle(Particle.SMOKE, spawnLoc, 20, 0.2, 0.2, 0.2, 0.01);
		spawnWorld.spawnParticle(Particle.FIREWORK, spawnLoc, 20, 0.2, 0.4, 0.2, 0.05);
		spawnWorld.spawnParticle(Particle.END_ROD, spawnLoc, 30, 0.2, 0.5, 0.2, 0.01);
		spawnWorld.playSound(spawnLoc, Sound.ENTITY_FIREWORK_ROCKET_BLAST, 1.0f, 1.2f);
		spawnWorld.playSound(spawnLoc, Sound.ENTITY_ENDER_DRAGON_FLAP, 0.8f, 2.0f);
		spawnWorld.playSound(spawnLoc, Sound.BLOCK_BEACON_ACTIVATE, 1.0f, 1.5f);
	}

    /**
     * 捕獲失敗時などに空のポケボールを返却する
     */
    private void dropPokeBall(Player player) {
        ItemManager itemManager = plugin.getItemManager();
        String playerUUID = player.getUniqueId().toString();
        String playerName = player.getName();
        ItemStack emptyBall = itemManager.createPokeBall(playerUUID,playerName);
        player.getWorld().dropItem(player.getLocation(), emptyBall);
    }
}
