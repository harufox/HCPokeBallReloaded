package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.CatTypeMapper;
import net.kyori.adventure.text.Component;

public class CatData implements EntityData {
    public String customName;
    public boolean adult;
    public boolean hasCustomName;
    public double health;
    public String collarColor;
    public String catType;


    @Override
    public String getType() {
        return "CAT";
    }

    public static CatData fromEntity(Cat cat) {
        CatData data = new CatData();
        data.customName = cat.getName();
        data.hasCustomName = cat.isCustomNameVisible();
        data.adult = cat.isAdult();
        data.health = cat.getHealth();
        data.catType = CatTypeMapper.toString(cat.getCatType());
        data.collarColor = cat.getCollarColor().name();
        return data;
    }

    @Override
    public void applyTo(Entity entity) {
       if (!(entity instanceof Cat)) return;
        applyTo((Cat) entity);
    }
    
    public void applyTo(Cat cat) {
        cat.customName(Component.text(customName));
        cat.setCustomNameVisible(hasCustomName);
        if(adult){
            cat.setAdult();
        }else{
            cat.setBaby();
        }
        cat.setTamed(true); // ← オーナー設定前提で true に
        cat.setHealth(health);
        cat.setCollarColor(DyeColor.valueOf(collarColor));
        cat.setCatType(CatTypeMapper.fromString(catType));
        cat.setSitting(true);
    }
}
