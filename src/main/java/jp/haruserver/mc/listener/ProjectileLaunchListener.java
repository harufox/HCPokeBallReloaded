package jp.haruserver.mc.listener;

import java.util.List;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.HCPokeBall;
import net.md_5.bungee.api.ChatColor;

public class ProjectileLaunchListener implements Listener{

	@EventHandler
	public void onPlayerLaunchPokeBall(ProjectileLaunchEvent e) {
		//卵じゃないなら即終了
		if(!(e.getEntity().getType().equals(EntityType.EGG))) return;
		//プレイヤーじゃないなら終了
		if(!(e.getEntity().getShooter() instanceof Player)) return;
		Player player = (Player)e.getEntity().getShooter();
		if(HCPokeBall.isDebug) {
			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + ":Player判定");
		}
		//表示名テスト
		if(HCPokeBall.isDebug) {
			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + "表示テスト:" + player.getInventory().getItemInMainHand().getItemMeta().getDisplayName());
		}

		ItemStack throwEgg = player.getInventory().getItemInMainHand();
		if(HCPokeBall.isDebug) {
			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"アイテムは存在。");
		}
		if(throwEgg == null) return;
		//PokeBallか判定
		if(throwEgg.getItemMeta().getDisplayName().startsWith(ChatColor.AQUA + "PokeBall")) {
			if((HCPokeBall.playerThrowedEmptyBallProjectile.containsKey(e.getEntity().getEntityId()))) return;
			if((HCPokeBall.playerThrowedBallProjectile.containsKey(e.getEntity().getEntityId()))) return;
			List<String> eggLore = throwEgg.getItemMeta().getLore();
			switch(eggLore.get(0)) {
			//カラのボールだった場合
			case "[Empty]":
				HCPokeBall.playerThrowedEmptyBallProjectile.put(e.getEntity().getEntityId(),player);
				HCPokeBall.cancelChicken.add(player);
				if(HCPokeBall.isDebug) {
					player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"空のボールを投げた");
				}
				break;
			case "WOLF":
				HCPokeBall.playerThrowedBallProjectile.put(e.getEntity().getEntityId(),throwEgg);
				HCPokeBall.cancelChicken.add(player);
				if(HCPokeBall.isDebug) {
					player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"オオカミ入りのボールを投げた");
				}
				break;
			case "CAT":
				HCPokeBall.playerThrowedBallProjectile.put(e.getEntity().getEntityId(),throwEgg);
				HCPokeBall.cancelChicken.add(player);
				if(HCPokeBall.isDebug) {
					player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"ネコ入りのボールを投げた");
				}
				break;
			case "PARROT":
				HCPokeBall.playerThrowedBallProjectile.put(e.getEntity().getEntityId(),throwEgg);
				HCPokeBall.cancelChicken.add(player);
				if(HCPokeBall.isDebug) {
					player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"オウム入りのボールを投げた");
				}
				break;
			}
		}

//		//投げたやつが空のPokeBallを投げたか判定
//		if(HCPokeBall.isDebug) {
//			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + ":PokeBallを投げたやつであるか判定");
//		}
//		if(HCPokeBall.playerThrowedEmptyBall.contains(player)) {
//			if(HCPokeBall.isDebug) {
//				player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + ":引き渡し変数に代入 launchlistener -> hit listener");
//			}
//			HCPokeBall.playerThrowedEmptyBall.remove(player);
//			HCPokeBall.playerThrowedEmptyBallProjectile.put(player,e.getEntity().getEntityId());
//		//投げたやつがはいってるPokeBallを投げたか判定
//		}else if(HCPokeBall.playerThrowedBall.containsKey(player)){
//			if(HCPokeBall.isDebug) {
//				player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() + ":引き渡し変数に代入2 launchlistener -> hit listener");
//			}
//			HCPokeBall.playerThrowedBallProjectile.put(player,e.getEntity().getEntityId());
//		}
	return;
	}
}
