package jp.haruserver.mc.hcpokeball;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import jp.haruserver.mc.hcpokeball.listener.HCPokeBallCommands;
import jp.haruserver.mc.hcpokeball.listener.PlayerJoinListener;
import jp.haruserver.mc.hcpokeball.listener.ProjectileHitListener;
import jp.haruserver.mc.hcpokeball.util.CustomConfig;
import jp.haruserver.mc.hcpokeball.util.ItemManager;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class HCPokeBall  extends JavaPlugin{

	private final String CONFIG_NAME = "config";
	private CustomConfig config = null;
	//プラグイン設定用マップ
	private HashMap<String,String> configMap = new HashMap<String,String>();
	private HashMap<String,List<String>> configListMap = new HashMap<String,List<String>>();

	//ユーティリティクラスロード
	ItemManager im = new ItemManager(this);
	PokeBallKeys pbk = new PokeBallKeys(this);

	private static Plugin plugin;
	
	public void onLoad(){
		config = new CustomConfig(this,CONFIG_NAME + ".yml");
		loadConfig();
	}

	public void onEnable() {
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new ProjectileHitListener(this),this);
		pm.registerEvents(new PlayerJoinListener(this),this);

		//コマンド登録
		HCPokeBallCommands executer = new HCPokeBallCommands(this);
		getCommand("hcpb").setExecutor(executer);

		//レシピ登録
		ItemStack pokeball = im.createBlankPokeBall();
		NamespacedKey recipeKey = new NamespacedKey(this, "pokeball");
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

		plugin = this;

	}

	public void ondisable() {

	}

	/**
	 * プラグインを取得します。
	 */
	public static Plugin getPlugin() {
		return plugin;
	}

	/**
	 * コンフィグをロードします。
	 */
	public void loadConfig() {
		if (!config.isConfigExist()) {
			config.saveDefaultConfig();
			config.saveConfig();
		}

		configMap.clear();
		configListMap.clear();

		FileConfiguration cfg = config.getConfig();

		for (String key : cfg.getKeys(false)) {
			if (cfg.isString(key)) {
				configMap.put(key, cfg.getString(key));
			} else if (cfg.isList(key)) {
				List<String> list = cfg.getStringList(key);
				configListMap.put(key, list);
			} else {
				plugin.getLogger().warning("未対応のコンフィグ形式: " + key);
			}
		}
	}

	public PokeBallKeys getPokeBallKeys(){
		return this.pbk;
	}

	public ItemManager getItemManager(){
		return this.im;
	}

	public HashMap<String,String> getConfigMap(){
		return this.configMap;
	}

	public HashMap<String,List<String>> getConfigMapList(){
		return this.configListMap;
	}
}
