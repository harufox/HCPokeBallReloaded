package jp.haruserver.mc.hcpokeball.listener;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;

import jp.haruserver.mc.hcpokeball.HCPokeBall;
import jp.haruserver.mc.hcpokeball.util.PokeBallKeys;

public class PlayerCraftItemListener  implements Listener {

    private final HCPokeBall plugin;

    public PlayerCraftItemListener(HCPokeBall plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCraftPokeBall(CraftItemEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) return;
        if (!(event.getRecipe() instanceof ShapedRecipe)) return;

        ShapedRecipe recipe = (ShapedRecipe) event.getRecipe();
        NamespacedKey recipeKey = recipe.getKey();

        // PokéBallレシピかどうか判定
        if (!recipeKey.getKey().equals("pokeball")) return;

        Player player = (Player) event.getWhoClicked();
        ItemStack result = event.getCurrentItem();

        // ownerUUID を設定して上書き
        PokeBallKeys pokeBallKeys = plugin.getPokeBallKeys();
        String playerUUID = player.getUniqueId().toString();
        pokeBallKeys.setOwnerUUID(result, playerUUID);

        // 結果に反映（Inventoryクリック時の結果スロットを書き換える）
        event.setCurrentItem(result);
    }
}