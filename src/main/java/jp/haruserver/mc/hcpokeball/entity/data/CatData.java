package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.DyeColor;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.CatTypeMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class CatData implements EntityData {
    public String customName;
    public boolean adult;
    public boolean hasCustomName;
    public double health;

    //首輪の色
    public String collarColor;
    //模様
    public String catType;


    @Override
    public String getType() {
        return "CAT";
    }

    public static CatData fromEntity(Cat cat) {
        CatData data = new CatData();
        if(cat.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(cat.customName());
        }
        data.hasCustomName = cat.isCustomNameVisible();
        data.adult = cat.isAdult();
        data.health = cat.getHealth();
        data.catType = CatTypeMapper.toString(cat.getCatType());
        data.collarColor = cat.getCollarColor().name();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
       if (!(entity instanceof Cat)) return;
        applyTo((Cat) entity,player);
    }
    
    public void applyTo(Cat cat,Player player) {
        if(customName != null){
            cat.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        cat.setCustomNameVisible(hasCustomName);
        if(adult){
            cat.setAdult();
        }else{
            cat.setBaby();
        }
        cat.setTamed(true);
        cat.setHealth(health);
        cat.setCollarColor(DyeColor.valueOf(collarColor));
        cat.setCatType(CatTypeMapper.fromString(catType));
        cat.setSitting(true);
        cat.setOwner(player);
    }
}
