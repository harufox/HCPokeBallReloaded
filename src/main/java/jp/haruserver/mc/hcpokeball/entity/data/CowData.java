package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.CowVariantMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class CowData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String variant;
    
    @Override
    public String getType() {
        return "COW";
    }

    public static CowData fromEntity(Cow cow) {
        CowData data = new CowData();
        if(cow.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(cow.customName());
        }
        data.hasCustomName = cow.isCustomNameVisible();
        data.adult = cow.isAdult();
        data.health = cow.getHealth();
        data.variant = CowVariantMapper.toString(cow.getVariant());
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Cow)) return;
        applyTo((Cow) entity,player);
    }

    public void applyTo(Cow cow,Player player) {
        if(customName != null){
            cow.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        cow.setCustomNameVisible(hasCustomName);
        if (adult) {
            cow.setAdult();
        } else {
            cow.setBaby();
        }

        cow.setVariant(CowVariantMapper.fromString(variant));

        cow.setHealth(health);

    }
}
