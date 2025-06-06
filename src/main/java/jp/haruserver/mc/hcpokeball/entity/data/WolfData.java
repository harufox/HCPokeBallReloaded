package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import jp.haruserver.mc.hcpokeball.util.mapper.WolfVariantMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class WolfData implements EntityData {
    public String customName;
    public boolean adult;
    public boolean hasCustomName;
    public double health;

    //首輪の色
    public String collarColor;

    //種族
    public String variant;
    public String inventoryBase64;

    @Override
    public String getType() {
        return "WOLF";
    }

    public static WolfData fromEntity(Wolf wolf) {
        WolfData data = new WolfData();
        if(wolf.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(wolf.customName());
        }
        data.hasCustomName = wolf.isCustomNameVisible();
        data.adult = wolf.isAdult();
        data.health = wolf.getHealth();
        data.variant = WolfVariantMapper.toString(wolf.getVariant());
        data.collarColor = wolf.getCollarColor().name();

        ItemStack contents = wolf.getEquipment().getItem(EquipmentSlot.BODY);;
        data.inventoryBase64 = ItemStackSerializer.toBase64(contents);

        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
       if (!(entity instanceof Wolf)) return;
        applyTo((Wolf) entity,player);
    }
    
    public void applyTo(Wolf wolf,Player player) {
        if(customName != null){
            wolf.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        wolf.setCustomNameVisible(hasCustomName);
        if(adult){
            wolf.setAdult();
        }else{
            wolf.setBaby();
        }
        wolf.setTamed(true);
        wolf.setHealth(health);
        wolf.setCollarColor(DyeColor.valueOf(collarColor));
        wolf.setVariant(WolfVariantMapper.fromString(variant));
        wolf.setSitting(true);

        ItemStack contents = ItemStackSerializer.fromBase64(inventoryBase64);
        wolf.getEquipment().setItem(EquipmentSlot.BODY, contents);
        wolf.setOwner(player);
    }
}
