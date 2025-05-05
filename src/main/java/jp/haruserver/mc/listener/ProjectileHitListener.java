package jp.haruserver.mc.listener;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.HCPokeBall;
import jp.haruserver.mc.util.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class ProjectileHitListener implements Listener{

	ItemManager im = new ItemManager();

	@EventHandler
	public void onPokeBallHit(ProjectileHitEvent e) {
		//まず投げたやつがPlayerか判定
		if(!(e.getEntity().getShooter() instanceof Player)) return;
		Player player = (Player)e.getEntity().getShooter();
		Integer entityId = e.getEntity().getEntityId();
		if(HCPokeBall.isDebug) {
			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:Player判定終了");
		}
		//空のpokeballを投げたやつか判定
		if(HCPokeBall.isDebug) {
			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + ":ボール投げたやつ判定");
		}
		if(HCPokeBall.playerThrowedEmptyBallProjectile.containsKey(entityId)) {
			//当たったのがエンティティか判定、エンティティでないなら終了
			if(e.getHitEntity() == null) {
				player.sendMessage(ChatColor.AQUA + "ボールははずれた・・・");
				ItemStack pokeball = HCPokeBall.pokeBall;
				pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"[Empty]");
				player.getWorld().dropItem(player.getLocation(), pokeball);
				HCPokeBall.playerThrowedEmptyBallProjectile.remove(entityId);
				return;
			}
			if(e.getHitEntity() instanceof Wolf) {
				Wolf wolf = (Wolf)e.getHitEntity();
				if(wolf.isTamed()) {
					if(!(wolf.getOwner().getUniqueId().equals(player.getUniqueId()))) {
						player.sendMessage(ChatColor.AQUA + "自分のオオカミではないようだ・・・");
						return;
					}
					//捕獲処理
					if(HCPokeBall.isDebug) {
						player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + ":捕獲処理");
					}
					String wolfname = wolf.getCustomName();
					String isAdult;
					if(wolf.isAdult()) {
						isAdult = "大人";
					}else {
						isAdult = "子供";
					}
					String wolfcolor = wolf.getCollarColor().toString();
					Bukkit.getLogger().info("[HCPB]Wolf:Player:" + player.getDisplayName() + " Name:" + wolfname + " isAdult:" + String.valueOf(isAdult) + " CollarColor:" + wolfcolor);
					ItemStack pokeball = HCPokeBall.pokeBall;
					pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"WOLF",player.getName(),wolfname,isAdult,wolfcolor);
					//オオカミの位置にドロップ
					e.getHitEntity().getWorld().dropItem(e.getHitEntity().getLocation(), pokeball);
					//オオカミ消去
					e.getHitEntity().remove();
				}else {
					player.sendMessage(ChatColor.AQUA + "仲良くないようだ・・・");
				}
			}else if(e.getHitEntity() instanceof Cat) {
				Cat cat = (Cat)e.getHitEntity();
				if(cat.isTamed()) {
					if(!(cat.getOwner().getUniqueId().equals(player.getUniqueId()))) {
						player.sendMessage(ChatColor.AQUA + "自分のネコではないようだ・・・");
						return;
					}
					String catname = cat.getCustomName();
					String isAdult;
					if(cat.isAdult()) {
						isAdult = "大人";
					}else {
						isAdult = "子供";
					}
					String catcolor = cat.getCollarColor().toString();
					String catType = cat.getCatType().toString();
					Bukkit.getLogger().info("[HCPB]Cat:Player:" + player.getDisplayName() + " Name:" + catname + " isAdult:" + String.valueOf(isAdult) + " CollarColor:" + catcolor + " CatType:" + catType);
					ItemStack pokeball = HCPokeBall.pokeBall;
					pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"CAT",player.getName(),catname,isAdult,catcolor,catType);
					//ネコの位置にドロップ
					e.getHitEntity().getWorld().dropItem(e.getHitEntity().getLocation(), pokeball);
					//ネコ消去
					e.getHitEntity().remove();
				}else {
					player.sendMessage(ChatColor.AQUA + "仲良くないようだ・・・");
				}
			}else if(e.getHitEntity() instanceof Parrot) {
				Parrot parrot = (Parrot)e.getHitEntity();
				if(parrot.isTamed()) {
					if(!(parrot.getOwner().getUniqueId().equals(player.getUniqueId()))) {
						player.sendMessage(ChatColor.AQUA + "オウムではないようだ・・・");
						return;
					}
					String parotname = parrot.getCustomName();
					String parrotcolor = parrot.getVariant().toString();
					Bukkit.getLogger().info("[HCPB]Parrot:Player:" + player.getDisplayName() + " Name:" + parotname + " Variant:" + parrotcolor);
					ItemStack pokeball = HCPokeBall.pokeBall;
					pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"PARROT",player.getName(),parotname,parrotcolor);
					//オウムの位置にドロップ
					e.getHitEntity().getWorld().dropItem(e.getHitEntity().getLocation(), pokeball);
					//オウム消去
					e.getHitEntity().remove();
				}else {
					player.sendMessage(ChatColor.AQUA + "仲良くないようだ・・・");
				}
			}else {
				player.sendMessage(ChatColor.AQUA + "捕まえることができないようだ・・・");
			}
		//登録解除
		HCPokeBall.playerThrowedEmptyBallProjectile.remove(entityId);
		return;
		//中身入りのボールだった場合
		}else if(HCPokeBall.playerThrowedBallProjectile.containsKey(entityId)){
			List<String> eggLore = HCPokeBall.playerThrowedBallProjectile.get(entityId).getItemMeta().getLore();
			switch(eggLore.get(0)) {
			//オオカミだった場合
			case "WOLF":
				Wolf wolf = (Wolf) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.WOLF);
				wolf.setTamed(true);
				wolf.setSitting(true);
				wolf.setOwner(player);
				wolf.setCustomName(eggLore.get(2));
				if(eggLore.get(3).equals("大人")) {
					wolf.setAdult();
				}else {
					wolf.setBaby();
				}
				wolf.setCollarColor(DyeColor.valueOf(eggLore.get(4)));
			//ネコ
			case "CAT":
				Cat cat = (Cat) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.CAT);
				cat.setTamed(true);
				cat.setSitting(true);
				cat.setOwner(player);
				cat.setCustomName(eggLore.get(2));
				if(eggLore.get(3).equals("大人")) {
					cat.setAdult();
				}else {
					cat.setBaby();
				}
				cat.setCollarColor(DyeColor.valueOf(eggLore.get(4)));
				cat.setCatType(Cat.Type.valueOf(eggLore.get(5)));
				break;
			case "PARROT":
				Parrot parrot = (Parrot) e.getEntity().getWorld().spawnEntity(e.getEntity().getLocation(), EntityType.PARROT);
				parrot.setTamed(true);
				parrot.setSitting(true);
				parrot.setOwner(player);
				parrot.setCustomName(eggLore.get(2));
				parrot.setVariant(Parrot.Variant.valueOf(eggLore.get(3)));
				break;
			}
			ItemStack pokeball = new ItemStack(Material.EGG);
			pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"[Empty]");
			player.getWorld().dropItem(player.getLocation(), pokeball);
			HCPokeBall.playerThrowedBallProjectile.remove(entityId);
			return;
		}
	return;
	}
}
