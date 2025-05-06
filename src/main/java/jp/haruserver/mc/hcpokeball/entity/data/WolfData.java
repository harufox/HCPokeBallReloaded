package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.Component;

public class WolfData implements EntityData {
    public String customName;
    public boolean adult;
    public float health;
    public String collarColor;
    public String variant;


    @Override
    public String getType() {
        return "WOLF";
    }

    public static WolfData fromEntity(Wolf wolf) {
        WolfData data = new WolfData();
        data.customName = wolf.name().toString();
        data.adult = wolf.isAdult();
        data.health = (float) wolf.getHealth();
        data.variant = wolf.getVariant().toString();
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
        if(adult){
            wolf.setAdult();
        }else{
            wolf.setBaby();
        }
        wolf.setTamed(true); // ← オーナー設定前提で true に
        wolf.setHealth(health);
        wolf.setCollarColor(DyeColor.valueOf(collarColor));
    }
}
