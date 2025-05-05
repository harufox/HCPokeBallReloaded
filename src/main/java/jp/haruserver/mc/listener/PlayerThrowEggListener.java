package jp.haruserver.mc.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEggThrowEvent;

import jp.haruserver.mc.HCPokeBall;
import net.md_5.bungee.api.ChatColor;

public class PlayerThrowEggListener implements Listener{
	@EventHandler
	public void onPlayerThrowEgg(PlayerEggThrowEvent e) {
		if(HCPokeBall.cancelChicken.contains(e.getPlayer())) {
			e.setHatching(false);
			HCPokeBall.cancelChicken.remove(e.getPlayer());
			if(HCPokeBall.isDebug) {
				e.getPlayer().sendMessage(ChatColor.AQUA + "[HCPB]:Debug:"+ e.getPlayer().getName() + "鶏羽化を抑止");
			}
		}
	}
}
