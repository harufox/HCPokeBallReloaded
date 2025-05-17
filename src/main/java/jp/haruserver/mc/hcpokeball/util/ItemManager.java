package jp.haruserver.mc.hcpokeball.util;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class ItemManager {

	private final HCPokeBall plugin;
    public ItemManager(HCPokeBall plugin) {
        this.plugin = plugin;
    }

	//pokeballのベースを生成する。
	public ItemStack createBlankPokeBall(){
		//pokeballにする処理
		ItemStack pokeBall = new ItemStack(Material.EGG);
		pokeBall.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);

		ItemMeta pokeballmeta = pokeBall.getItemMeta();
		pokeballmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pokeballmeta.setUnbreakable(true);

		//アイテム名を設定
		String itemName = "PokeBall";
		pokeballmeta.displayName(Component.text(itemName, NamedTextColor.AQUA));
		pokeBall.setItemMeta(pokeballmeta);
		setLine(pokeBall, Component.text("[Empty]", NamedTextColor.DARK_PURPLE), 1);
		return pokeBall;
	
	}

	//プレイヤー専用pokeballを作成する。
	public ItemStack createPokeBall(String UUID,String ownerName){
		ItemStack pokeBall = createBlankPokeBall();
		PokeBallKeys pokeBakkBallKeys = plugin.getPokeBallKeys();
		pokeBakkBallKeys.setOwnerUUID(pokeBall, UUID);
		setLine(pokeBall, Component.text("OWNER:" + ownerName, NamedTextColor.DARK_PURPLE), 2);
		return pokeBall;
	}

	//捕獲後pokeballを作成する。
	public ItemStack createCapturedPokeBall(String petName,String playerName,String playerUUID,String entityTypeString,String json,Material eggMaterial){
		PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
		ItemStack pokeBall = new ItemStack(eggMaterial);
		pokeBall.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
		ItemMeta pokeballmeta = pokeBall.getItemMeta();
		pokeballmeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		pokeballmeta.setUnbreakable(true);

		//アイテム名を設定
		pokeballmeta.displayName(Component.text(petName + " が入ったPokeBall", NamedTextColor.AQUA));
		pokeBall.setItemMeta(pokeballmeta);
		pokeBallKeys.setOwnerUUID(pokeBall, playerUUID);
		pokeBallKeys.setNbtString(pokeBall, json);
		pokeBallKeys.setEntityType(pokeBall, entityTypeString);
		setLine(pokeBall, Component.text("PETNAME:" + petName, NamedTextColor.AQUA), 1);
		setLine(pokeBall, Component.text("OWNER:" + playerName, NamedTextColor.DARK_PURPLE), 2);
		setLine(pokeBall, Component.text("SPECIES:" + entityTypeString, NamedTextColor.YELLOW), 3);
		return pokeBall;
	}

	//アイテム説明文を設定する
	public void setLine(ItemStack item, Component lineText,int lineNumber) {

        ItemMeta meta = item.getItemMeta();
        List<Component> lore = meta.lore() != null ? new ArrayList<>(meta.lore()) : new ArrayList<>();

        //lore のサイズが lineNumber に満たない場合、空行で補完
        while (lore.size() < lineNumber) {
            lore.add(Component.empty());
        }

        //指定行（0ベースで lineNumber - 1）にテキスト設定
        lore.set(lineNumber - 1, lineText);

        meta.lore(lore);
        item.setItemMeta(meta);
	}

}
