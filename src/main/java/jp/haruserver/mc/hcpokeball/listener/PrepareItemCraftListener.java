package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.PrepareItemCraftEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PrepareItemCraftListener implements Listener {

    private final HCPokeBall plugin;

    public PrepareItemCraftListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrepareCraftItem(PrepareItemCraftEvent event) {
        if (!(event.getRecipe() instanceof ShapedRecipe)) return;

        ShapedRecipe recipe = (ShapedRecipe) event.getRecipe();
        NamespacedKey recipeKey = recipe.getKey();

        //PokéBallレシピかどうか判定
        if (!recipeKey.getKey().equals("pokeball")) return;

        //プレイヤー取得（クラフトビューアーの一人目＝クラフトしたプレイヤー）
        if (!(event.getView().getPlayer() instanceof Player player)) return;

        ItemStack result = event.getInventory().getResult();
        if (result == null) return;

        //ownerUUID を設定して上書き
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
        String playerUUID = player.getUniqueId().toString();
        pokeBallKeys.setOwnerUUID(result, playerUUID);

        //結果に反映（Inventoryクリック時の結果スロットを書き換える）
        event.getInventory().setResult(result);

    }
}