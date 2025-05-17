package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.DyeColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class SheepData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //毛が生えているか
    public boolean isSheared;

    //色
    public String color;
    
    @Override
    public String getType() {
        return "SHEEP";
    }

    public static SheepData fromEntity(Sheep sheep) {
        SheepData data = new SheepData();
        if(sheep.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(sheep.customName());
        }
        data.hasCustomName = sheep.isCustomNameVisible();
        data.adult = sheep.isAdult();
        data.health = sheep.getHealth();
        data.isSheared = sheep.isSheared();
        data.color = sheep.getColor().toString();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Sheep)) return;
        applyTo((Sheep) entity,player);
    }

    public void applyTo(Sheep sheep,Player player) {
        if(customName != null){
            sheep.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        sheep.setCustomNameVisible(hasCustomName);
        if (adult) {
            sheep.setAdult();
        } else {
            sheep.setBaby();
        }

        sheep.setColor(DyeColor.valueOf(color));

        sheep.setHealth(health);

    }
}
