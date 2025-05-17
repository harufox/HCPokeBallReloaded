package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class PolarBearData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;
    
    @Override
    public String getType() {
        return "POLAR_BEAR";
    }

    public static PolarBearData fromEntity(PolarBear polarBear) {
        PolarBearData data = new PolarBearData();
        if(polarBear.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(polarBear.customName());
        }
        data.hasCustomName = polarBear.isCustomNameVisible();
        data.adult = polarBear.isAdult();
        data.health = polarBear.getHealth();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof PolarBear)) return;
        applyTo((PolarBear) entity,player);
    }

    public void applyTo(PolarBear polarBear,Player player) {
        if(customName != null){
            polarBear.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        polarBear.setCustomNameVisible(hasCustomName);
        if (adult) {
            polarBear.setAdult();
        } else {
            polarBear.setBaby();
        }
        polarBear.setHealth(health);

        polarBear.setStanding(true);

    }
}
