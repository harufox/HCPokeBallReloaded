package jp.haruserver.mc.listener;

import org.bukkit.event.Listener;

public class PlayerInteractListener implements Listener{

//	@EventHandler(priority = EventPriority.HIGH)
//	public void onPlayerEggThrow(PlayerInteractEvent e) {
//		if(!e.getAction().equals(Action.LEFT_CLICK_AIR)) return;
//		Player player = e.getPlayer();
//		ItemStack throwEgg = e.getItem();
//		if(HCPokeBall.isDebug) {
//			player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"アイテムは存在。");
//		}
//		if(throwEgg == null) return;
//		//PokeBallか判定
//		if(throwEgg.getItemMeta().getDisplayName().startsWith(ChatColor.AQUA + "PokeBall")) {
//			if((HCPokeBall.playerThrowedBall.containsKey(player))) return;
//			if((HCPokeBall.playerThrowedEmptyBall.contains(player))) return;
//			List<String> eggLore = throwEgg.getItemMeta().getLore();
//			switch(eggLore.get(0)) {
//			//カラのボールだった場合
//			case "[Empty]":
//				HCPokeBall.playerThrowedEmptyBall.add(player);
//				HCPokeBall.cancelChicken.add(player);
//				if(HCPokeBall.isDebug) {
//					player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"空のボールを投げた");
//				}
//				break;
//			case "WOLF":
//				HCPokeBall.playerThrowedBall.put(player,throwEgg);
//				HCPokeBall.cancelChicken.add(player);
//				if(HCPokeBall.isDebug) {
//					player.sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ player.getName() +"オオカミ入りのボールを投げた");
//				}
//				break;
//			}
//		}
//	}
}
