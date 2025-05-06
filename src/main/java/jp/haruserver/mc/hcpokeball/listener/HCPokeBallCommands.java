package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.ItemManager;
import net.md_5.bungee.api.ChatColor;



public class HCPokeBallCommands implements CommandExecutor{
	
	private final HCPokeBall plugin;
	
    public HCPokeBallCommands(HCPokeBall plugin) {
        this.plugin = plugin;
    }
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		if(args.length == 0){
			sender.sendMessage(ChatColor.AQUA + "---------------------[INFO]---------------------");
			sender.sendMessage(ChatColor.AQUA + "/hcpb get - ボールを入手します");
			sender.sendMessage(ChatColor.AQUA + "---------------------[INFO]---------------------");
			return false;
		}

		ItemManager itemManager = plugin.getItemManager();
		Player player = (Player)sender;
		String playerUUID = player.getUniqueId().toString();
		//pokeball取得処理
		if (args[0].equalsIgnoreCase("get")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				ItemStack pokeball = itemManager.createPokeBall(playerUUID);
				player.getWorld().dropItem(player.getLocation(), pokeball);
			}
		}
		if (args[0].equalsIgnoreCase("get2")){

		}
		return false;
	}
}
