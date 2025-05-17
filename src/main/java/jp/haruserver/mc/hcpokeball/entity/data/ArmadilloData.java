package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Armadillo;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class ArmadilloData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    @Override
    public String getType() {
        return "ARMADILLO";
    }

    public static ArmadilloData fromEntity(Armadillo armadillo) {
        ArmadilloData data = new ArmadilloData();
        if(armadillo.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(armadillo.customName());
        }
        data.hasCustomName = armadillo.isCustomNameVisible();
        data.adult = armadillo.isAdult();
        data.health = armadillo.getHealth();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Armadillo)) return;
        applyTo((Armadillo) entity,player);
    }

    public void applyTo(Armadillo armadillo,Player player) {
        if(customName != null){
            armadillo.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        armadillo.setCustomNameVisible(hasCustomName);
        if (adult) {
            armadillo.setAdult();
        } else {
            armadillo.setBaby();
        }

        armadillo.setHealth(health);

    }
}
