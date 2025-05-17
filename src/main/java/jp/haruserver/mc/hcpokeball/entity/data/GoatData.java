package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Goat;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class GoatData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //左右角存在
    public boolean hasLeftHorn;
    public boolean hasRightHorn;

    //叫ぶヤギ
    public boolean isScreaming;
    
    @Override
    public String getType() {
        return "GOAT";
    }

    public static GoatData fromEntity(Goat goat) {
        GoatData data = new GoatData();
        if(goat.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(goat.customName());
        }
        data.hasCustomName = goat.isCustomNameVisible();
        data.adult = goat.isAdult();
        data.health = goat.getHealth();
        data.hasLeftHorn = goat.hasLeftHorn();
        data.hasRightHorn = goat.hasRightHorn();
        data.isScreaming = goat.isScreaming();

        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Goat)) return;
        applyTo((Goat) entity,player);
    }

    public void applyTo(Goat goat,Player player) {
        if(customName != null){
            goat.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        goat.setCustomNameVisible(hasCustomName);
        if (adult) {
            goat.setAdult();
        } else {
            goat.setBaby();
        }

        goat.setLeftHorn(hasLeftHorn);
        goat.setRightHorn(hasRightHorn);
        goat.setScreaming(isScreaming);

        goat.setHealth(health);

    }
}
