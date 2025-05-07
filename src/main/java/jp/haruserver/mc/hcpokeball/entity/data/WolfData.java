package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.WolfVariantMapper;
import net.kyori.adventure.text.Component;

public class WolfData implements EntityData {
    public String customName;
    public boolean adult;
    public boolean hasCustomName;
    public double health;
    public String collarColor;
    public String variant;


    @Override
    public String getType() {
        return "WOLF";
    }

    public static WolfData fromEntity(Wolf wolf) {
        WolfData data = new WolfData();
        data.customName = wolf.getName();
        data.hasCustomName = wolf.isCustomNameVisible();
        data.adult = wolf.isAdult();
        data.health = wolf.getHealth();
        data.variant = WolfVariantMapper.toString(wolf.getVariant());
        data.collarColor = wolf.getCollarColor().name();
        return data;
    }

    @Override
    public void applyTo(Entity entity) {
       if (!(entity instanceof Wolf)) return;
        applyTo((Wolf) entity);
    }
    
    public void applyTo(Wolf wolf) {
        wolf.customName(Component.text(customName));
        wolf.setCustomNameVisible(hasCustomName);
        if(adult){
            wolf.setAdult();
        }else{
            wolf.setBaby();
        }
        wolf.setTamed(true); // ← オーナー設定前提で true に
        wolf.setHealth(health);
        wolf.setCollarColor(DyeColor.valueOf(collarColor));
        wolf.setVariant(WolfVariantMapper.fromString(variant));
        wolf.setSitting(true);
    }
}
