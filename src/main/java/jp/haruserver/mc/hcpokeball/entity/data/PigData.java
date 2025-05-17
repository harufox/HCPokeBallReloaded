package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import jp.haruserver.mc.hcpokeball.util.mapper.PigVariantMapper;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class PigData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String variant;

    public String bodyBase64;
    
    @Override
    public String getType() {
        return "PIG";
    }

    public static PigData fromEntity(Pig pig) {
        PigData data = new PigData();
        if(pig.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(pig.customName());
        }
        data.hasCustomName = pig.isCustomNameVisible();
        data.adult = pig.isAdult();
        data.health = pig.getHealth();
        data.variant = PigVariantMapper.toString(pig.getVariant());
        ItemStack content = pig.getEquipment().getItem(EquipmentSlot.SADDLE);

        if(content != null){
            data.bodyBase64 = ItemStackSerializer.toBase64(content);
        }

        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Pig)) return;
        applyTo((Pig) entity,player);
    }

    public void applyTo(Pig pig,Player player) {
        if(customName != null){
            pig.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        pig.setCustomNameVisible(hasCustomName);
        if (adult) {
            pig.setAdult();
        } else {
            pig.setBaby();
        }

        pig.setVariant(PigVariantMapper.fromString(variant));
        if(bodyBase64 != null){
            ItemStack content = ItemStackSerializer.fromBase64(bodyBase64);
            pig.getEquipment().setItem(EquipmentSlot.SADDLE, content);
        }

        pig.setHealth(health);

    }
}
