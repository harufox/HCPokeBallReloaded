package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Donkey;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class DonkeyData implements EntityData {

    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;
    public double maxHealth;
    public double jumpStrength;
    public boolean isTamed;
    public boolean isCarryingChest;
    public boolean is;
    public String color;
    public String style;
    public String bodyBase64;
    public String inventoryBase64;

    @Override
    public String getType() {
        return "DONKEY";
    }

    public static DonkeyData fromEntity(Donkey donkey) {
        DonkeyData data = new DonkeyData();
        if(donkey.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(donkey.customName());
        }
        data.hasCustomName = donkey.isCustomNameVisible();
        data.adult = donkey.isAdult();
        data.health = donkey.getHealth();
        data.maxHealth = donkey.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
        data.isTamed = donkey.isTamed();
        ItemStack content = donkey.getEquipment().getItem(EquipmentSlot.SADDLE);
        if(content != null){
            data.bodyBase64 = ItemStackSerializer.toBase64(content);
        }
        data.isCarryingChest = donkey.isCarryingChest();
        if(data.isCarryingChest){
            ItemStack[] contents = donkey.getInventory().getContents();
            data.inventoryBase64 = ItemStackSerializer.toBase64Array(contents);
        }

        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Donkey)) return;
        applyTo((Donkey) entity,player);
    }

    public void applyTo(Donkey donkey,Player player) {
        if(customName != null){
            donkey.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        donkey.setCustomNameVisible(hasCustomName);
        if (adult) {
            donkey.setAdult();
        } else {
            donkey.setBaby();
        }
        donkey.setTamed(isTamed);
        donkey.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        donkey.setHealth(health);

        if(bodyBase64 != null){
            ItemStack content = ItemStackSerializer.fromBase64(bodyBase64);
            donkey.getEquipment().setItem(EquipmentSlot.SADDLE, content);
        }

        if (inventoryBase64 != null) {
            donkey.setCarryingChest(true); // 必ず先に設定
            ItemStack[] contents = ItemStackSerializer.fromBase64Array(inventoryBase64);
            donkey.getInventory().setContents(contents);
        }
        donkey.setOwner(player);
    }
}
