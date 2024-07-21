package jp.harucraft;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import jp.harucraft.listener.HCPokeBallCommands;
import jp.harucraft.listener.PlayerInteractListener;
import jp.harucraft.listener.PlayerThrowEggListener;
import jp.harucraft.listener.ProjectileHitListener;
import jp.harucraft.listener.ProjectileLaunchListener;
import jp.harucraft.util.ItemManager;
import net.md_5.bungee.api.ChatColor;

public class HCPokeBall  extends JavaPlugin{

	public static ItemStack pokeBall;
	public final PlayerInteractListener PlayerInteractListener = new PlayerInteractListener();
	public final ProjectileHitListener ProjectileHitListener = new ProjectileHitListener();
	public final ProjectileLaunchListener ProjectileLaunchListener = new ProjectileLaunchListener();
	public final PlayerThrowEggListener PlayerThrowEggListener = new PlayerThrowEggListener();
//	public static ArrayList<Player> playerThrowedEmptyBall;
//	public static HashMap<Player,ItemStack> playerThrowedBall;
	public static ArrayList<Player> cancelChicken;
	public static HashMap<Integer,Player> playerThrowedEmptyBallProjectile;
	public static HashMap<Integer,ItemStack> playerThrowedBallProjectile;
	public static boolean isDebug;
	ItemManager im = new ItemManager();
	public void onLoad(){

	}

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
//		pm.registerEvents(this.PlayerInteractListener,this);
		pm.registerEvents(this.ProjectileLaunchListener,this);
		pm.registerEvents(this.ProjectileHitListener,this);
		pm.registerEvents(this.PlayerThrowEggListener,this);
		registerGlow();

		//コマンド登録
		HCPokeBallCommands executer = new HCPokeBallCommands();
		getCommand("hcpb").setExecutor(executer);

		//光る雪玉にする処理
		pokeBall = new ItemStack(Material.EGG);
		pokeBall.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);

		ItemMeta pokeballmeta = pokeBall.getItemMeta();
		pokeballmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pokeBall.setItemMeta(pokeballmeta);

//		playerThrowedEmptyBall = new ArrayList<Player>();
//		playerThrowedBall = new HashMap<Player,ItemStack>();
		cancelChicken = new ArrayList<Player>();
		playerThrowedEmptyBallProjectile = new HashMap<Integer,Player>();
		playerThrowedBallProjectile = new HashMap<Integer,ItemStack>();
		isDebug = false;

		ItemStack pokeball = pokeBall;

		pokeball = im.applyToStack(pokeball,ChatColor.AQUA + "PokeBall",true,true,"[Empty]");

		NamespacedKey recipeKey = new NamespacedKey(this, "emerald_sword");
		ShapedRecipe recipe = new ShapedRecipe(recipeKey, pokeball);
		recipe.shape("ABC", "DEF", "GHI");
		recipe.setIngredient('A', Material.BONE);
		recipe.setIngredient('B', Material.LEAD);
		recipe.setIngredient('C', Material.SALMON);
		recipe.setIngredient('D', Material.SWEET_BERRIES);
		recipe.setIngredient('E', Material.EGG);
		recipe.setIngredient('F', Material.BAMBOO);
		recipe.setIngredient('G', Material.WHEAT_SEEDS);
		recipe.setIngredient('H', Material.REDSTONE_BLOCK);
		recipe.setIngredient('I', Material.GOLDEN_APPLE);
		Bukkit.addRecipe(recipe);

	}

	public void ondisable() {

	}


    public void registerGlow() {
        // try {
        //     Field f = Enchantment.class.getDeclaredField("acceptingNew");
        //     f.setAccessible(true);
        //     f.set(null, true);
        // }
        // catch (Exception e) {
        //     e.printStackTrace();
        // }
        // try {
        // 	NamespacedKey key = new NamespacedKey(this, getDescription().getName());
        // 	EggGlow glow = new EggGlow(key);
        // 	Enchantment.registerEnchantment(glow);
        // }
        // catch (IllegalArgumentException e){
        // }
        // catch(Exception e){
        // 	e.printStackTrace();
        // }
	}

}
