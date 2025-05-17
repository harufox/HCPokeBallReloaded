package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Camel;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class CamelData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;
    public boolean isTamed;

    //最大体力
    public double maxHealth;
    //ジャンプ力
    public double jumpStrength;

    public String inventoryBase64;

    @Override
    public String getType() {
        return "CAMEL";
    }

    public static CamelData fromEntity(Camel camel) {
        CamelData data = new CamelData();
        if(camel.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(camel.customName());
        }
        data.hasCustomName = camel.isCustomNameVisible();
        data.adult = camel.isAdult();
        data.health = camel.getHealth();
        data.isTamed = camel.isTamed();
        ItemStack[] contents = camel.getInventory().getContents();
        data.inventoryBase64 = ItemStackSerializer.toBase64Array(contents);
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Camel)) return;
        applyTo((Camel) entity,player);
    }

    public void applyTo(Camel camel,Player player) {
        if(customName != null){
            camel.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        camel.setCustomNameVisible(hasCustomName);
        if (adult) {
            camel.setAdult();
        } else {
            camel.setBaby();
        }
        camel.setTamed(isTamed);
        camel.setHealth(health);

        ItemStack[] contents = ItemStackSerializer.fromBase64Array(inventoryBase64);
        camel.getInventory().setContents(contents);
    }
}
