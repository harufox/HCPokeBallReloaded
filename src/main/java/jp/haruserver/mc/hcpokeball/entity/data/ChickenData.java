package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.ChickenVariantMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ChickenData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String variant;
    
    @Override
    public String getType() {
        return "CHICKEN";
    }

    public static ChickenData fromEntity(Chicken chicken) {
        ChickenData data = new ChickenData();
        if(chicken.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(chicken.customName());
        }
        data.hasCustomName = chicken.isCustomNameVisible();
        data.adult = chicken.isAdult();
        data.health = chicken.getHealth();
        data.variant = ChickenVariantMapper.toString(chicken.getVariant());
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Chicken)) return;
        applyTo((Chicken) entity,player);
    }

    public void applyTo(Chicken chicken,Player player) {
        if(customName != null){
            chicken.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        chicken.setCustomNameVisible(hasCustomName);
        if (adult) {
            chicken.setAdult();
        } else {
            chicken.setBaby();
        }

        chicken.setVariant(ChickenVariantMapper.fromString(variant));

        chicken.setHealth(health);

    }
}
