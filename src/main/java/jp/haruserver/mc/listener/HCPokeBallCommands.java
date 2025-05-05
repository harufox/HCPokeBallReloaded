package jp.haruserver.mc.listener;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.HCPokeBall;
import jp.haruserver.mc.util.ItemManager;
import net.md_5.bungee.api.ChatColor;



public class HCPokeBallCommands implements CommandExecutor{

	ItemManager im = new ItemManager();

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(args.length == 0){
			sender.sendMessage(ChatColor.AQUA + "---------------------[INFO]---------------------");
			sender.sendMessage(ChatColor.AQUA + "/hcpb get - ボールを入手します");
			sender.sendMessage(ChatColor.AQUA + "---------------------[INFO]---------------------");
			return false;
		}
		//pokeball取得処理
		if (args[0].equalsIgnoreCase("get")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				ItemStack pokeball = HCPokeBall.pokeBall.clone();
				pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"[Empty]");
				((Player)sender).getInventory().addItem(pokeball);
			}
		}
		if (args[0].equalsIgnoreCase("get2")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				ItemStack pokeball = new ItemStack(Material.EGG);
				pokeball.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
				pokeball.addEnchantment(Enchantment.SHARPNESS, 1);
				pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"[Empty]");
				((Player)sender).getInventory().addItem(pokeball);
			}
		}
		if (args[0].equalsIgnoreCase("debug")){
			if(!(sender instanceof ConsoleCommandSender)){
				if(!((Player)sender).hasPermission("hcpokeball.admin")){
					return false;
				}
				if(HCPokeBall.isDebug) {
					HCPokeBall.isDebug = false;
				}else {
					HCPokeBall.isDebug = true;
				}
				sender.sendMessage(ChatColor.AQUA + "デバッグモード変更:" + HCPokeBall.isDebug);
			}
		}
		return false;
	}
}
