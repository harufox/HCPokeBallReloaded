package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;
import org.bukkit.entity.Entity;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.mapper.ParrotVariantMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ParrotData implements EntityData {
    public String customName;
    public boolean adult;
    public boolean hasCustomName;
    public double health;
    public String variant;

    @Override
    public String getType() {
        return "PARROT";
    }

    public static ParrotData fromEntity(Parrot parrot) {
        ParrotData data = new ParrotData();
        if(parrot.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(parrot.customName());
        }
        data.hasCustomName = parrot.isCustomNameVisible();
        data.adult = parrot.isAdult();
        data.health = parrot.getHealth();
        data.variant = ParrotVariantMapper.toString(parrot.getVariant());
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Parrot)) return;
        applyTo((Parrot) entity,player);
    }
    
    public void applyTo(Parrot parrot,Player player) {
        if(customName != null){
            parrot.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        parrot.setCustomNameVisible(hasCustomName);
        if (adult) {
            parrot.setAdult();
        }else{
            parrot.setBaby();
        }
        parrot.setVariant(ParrotVariantMapper.fromString(variant));
        parrot.setHealth(health);
        parrot.setTamed(true);
        parrot.setOwner(player);
    }
}
