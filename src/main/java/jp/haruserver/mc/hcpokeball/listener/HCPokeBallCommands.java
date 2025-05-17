package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.ItemManager;
import jp.haruserver.mc.hcpokeball.util.MessageManager;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;
import net.kyori.adventure.text.Component;


public class HCPokeBallCommands implements CommandExecutor{
	
	private final HCPokeBall plugin;
	
    public HCPokeBallCommands(HCPokeBall plugin) {
        this.plugin = plugin;
    }
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if (!(sender instanceof Player)) return false;
		Player player = (Player)sender;
        MessageManager messageManager = plugin.getMessageManager();
		String messagePrefix = messageManager.getMessage(player, "pokeball.prefix");
		if(args.length == 0){
			player.sendMessage(messageManager.getMessage(player, "command.info"));
			player.sendMessage(messageManager.getMessage(player, "command.info.get"));
			player.sendMessage(messageManager.getMessage(player, "command.info"));
			return false;
		}
		ItemManager itemManager = plugin.getItemManager();

		String playerUUID = player.getUniqueId().toString();
		//pokeball取得処理
		if (args[0].equalsIgnoreCase("get")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				String playerName = player.getName();
				ItemStack pokeball = itemManager.createPokeBall(playerUUID,playerName);
				player.getWorld().dropItem(player.getLocation(), pokeball);
				player.sendMessage(messagePrefix + messageManager.getMessage(player, "command.get.success"));
			}
		}
		if (args[0].equalsIgnoreCase("give")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				Player givePlayer = Bukkit.getPlayer(args[1]);
				String givePlayerName = givePlayer.getName();
				String givePlayerUUID = givePlayer.getUniqueId().toString();
				ItemStack pokeball = itemManager.createPokeBall(givePlayerUUID,givePlayerName);
				givePlayer.getWorld().dropItem(givePlayer.getLocation(), pokeball);
				player.sendMessage(messagePrefix + messageManager.getMessage(player, "command.give.success"));
			}
		}
		if (args[0].equalsIgnoreCase("debug")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
				ItemStack pokeball = player.getInventory().getItemInMainHand();
				String ownerUUID = pokeBallKeys.getOwnerUUID(pokeball);
				String json = pokeBallKeys.getNbtString(pokeball);
				String type = pokeBallKeys.getEntityType(pokeball);

				player.sendMessage(Component.text(ownerUUID));
				player.sendMessage(Component.text(json));
				player.sendMessage(Component.text(type));
			}
		}
		return false;
	}
}
