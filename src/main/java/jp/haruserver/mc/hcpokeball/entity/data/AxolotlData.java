package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class AxolotlData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String variant;
    
    @Override
    public String getType() {
        return "AXOLOTL";
    }

    public static AxolotlData fromEntity(Axolotl axolotl) {
        AxolotlData data = new AxolotlData();
        if(axolotl.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(axolotl.customName());
        }
        data.hasCustomName = axolotl.isCustomNameVisible();
        data.adult = axolotl.isAdult();
        data.health = axolotl.getHealth();
        data.variant = axolotl.getVariant().toString();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Axolotl)) return;
        applyTo((Axolotl) entity,player);
    }

    public void applyTo(Axolotl axolotl,Player player) {
        if(customName != null){
            axolotl.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        axolotl.setCustomNameVisible(hasCustomName);
        if (adult) {
            axolotl.setAdult();
        } else {
            axolotl.setBaby();
        }

        axolotl.setVariant(Axolotl.Variant.valueOf(variant));
        //バケツから出したことにしてデスポーン阻止
        axolotl.setFromBucket(true);
        axolotl.setHealth(health);

    }
}
