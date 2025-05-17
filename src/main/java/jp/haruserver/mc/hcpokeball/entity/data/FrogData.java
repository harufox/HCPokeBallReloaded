package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Frog;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.FrogVariantMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class FrogData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String variant;
    
    @Override
    public String getType() {
        return "FROG";
    }

    public static FrogData fromEntity(Frog frog) {
        FrogData data = new FrogData();
        if(frog.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(frog.customName());
        }
        data.hasCustomName = frog.isCustomNameVisible();
        data.adult = frog.isAdult();
        data.health = frog.getHealth();
        data.variant = FrogVariantMapper.toString(frog.getVariant());
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Frog)) return;
        applyTo((Frog) entity,player);
    }

    public void applyTo(Frog frog,Player player) {
        if(customName != null){
            frog.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        frog.setCustomNameVisible(hasCustomName);
        if (adult) {
            frog.setAdult();
        } else {
            frog.setBaby();
        }

        frog.setVariant(FrogVariantMapper.fromString(variant));

        frog.setHealth(health);

    }
}
