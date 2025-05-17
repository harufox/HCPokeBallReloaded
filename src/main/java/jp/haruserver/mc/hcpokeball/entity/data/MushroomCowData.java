package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class MushroomCowData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String variant;
    
    @Override
    public String getType() {
        return "MOOSHROOM";
    }

    public static MushroomCowData fromEntity(MushroomCow mushroomCow) {
        MushroomCowData data = new MushroomCowData();
        if(mushroomCow.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(mushroomCow.customName());
        }
        data.hasCustomName = mushroomCow.isCustomNameVisible();
        data.adult = mushroomCow.isAdult();
        data.health = mushroomCow.getHealth();
        data.variant = mushroomCow.getVariant().toString();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof MushroomCow)) return;
        applyTo((MushroomCow) entity,player);
    }

    public void applyTo(MushroomCow mushroomCow,Player player) {
        if(customName != null){
            mushroomCow.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        mushroomCow.setCustomNameVisible(hasCustomName);
        if (adult) {
            mushroomCow.setAdult();
        } else {
            mushroomCow.setBaby();
        }

        mushroomCow.setVariant(MushroomCow.Variant.valueOf(variant));

        mushroomCow.setHealth(health);

    }
}
