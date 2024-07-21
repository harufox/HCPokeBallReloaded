package jp.harucraft.util;

import java.util.Arrays;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemManager {

	/**
	 * 引数指定のアイテムを返す
	 *
	 * @param stack アイテム
	 * @param name アイテムに付ける名前
	 * @param lore 可変長引数。アイテムの説明を入力
	 * @return 設定済みアイテムスタック
	 */
	public ItemStack applyToStack(ItemStack stack , String name ,Boolean isGlow,Boolean isUnbreakable, String...lore){
		ItemStack createitem = stack;
		ItemMeta meta = createitem.getItemMeta();
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		if(isUnbreakable){
			meta.setUnbreakable(true);
		}
		if(isGlow){
			//
		}
		createitem.setItemMeta(meta);
		return createitem;
	}
	/**
	 * こんな風に呼んでね(stack , Enchantment.<name> , <level (int)> , Enchantment.<name> , <level (int)>
	 * @param stack ｴﾝﾁｬﾝﾄを付与するアイテム
	 * @return ｴﾝﾁｬﾝﾄ付与済みアイテムスタック
	 */
	public ItemStack applyEnchantments(ItemStack stack , Object... ench){
		if(ench.length%2 != 0) return null;
		for(int i = 0 ; i < ench.length ; i += 2){
			Enchantment e = (Enchantment) ench[i];
			Integer lvl = Integer.parseInt(ench[i+1].toString());
			stack.addEnchantment(e, lvl);
		}
		return stack;
	}
}
