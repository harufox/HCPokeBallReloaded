package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class LlamaData implements EntityData {
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;
    public boolean isTamed;

    //最大体力
    public double maxHealth;

    //チェストを運んでいるか
    public boolean isCarryingChest;
    public String bodyBase64;
    public String inventoryBase64;

    //チェストサイズにかかわる強さ
    public int strength;

    //体色
    public String color;

    @Override
    public String getType() {
        return "LLAMA";
    }

    public static LlamaData fromEntity(Llama llama) {
        LlamaData data = new LlamaData();
        if(llama.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(llama.customName());
        }
        data.hasCustomName = llama.isCustomNameVisible();
        data.adult = llama.isAdult();
        data.health = llama.getHealth();
        data.maxHealth = llama.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
        data.strength =  llama.getStrength();
        data.isTamed = llama.isTamed();
        data.color = llama.getColor().toString();
        
        ItemStack content = llama.getEquipment().getItem(EquipmentSlot.BODY);
        if(content != null){
            data.bodyBase64 = ItemStackSerializer.toBase64(content);
        }
        data.isCarryingChest = llama.isCarryingChest();
        if(data.isCarryingChest){
            ItemStack[] contents = llama.getInventory().getContents();
            data.inventoryBase64 = ItemStackSerializer.toBase64Array(contents);
        }

        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Llama)) return;
        applyTo((Llama) entity,player);
    }

    public void applyTo(Llama llama,Player player) {
        if(customName != null){
            llama.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        llama.setCustomNameVisible(hasCustomName);
        if (adult) {
            llama.setAdult();
        } else {
            llama.setBaby();
        }
        llama.setTamed(isTamed);
        llama.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        llama.setHealth(health);
        llama.setStrength(strength);
        llama.setOwner(player);
        llama.setColor(Llama.Color.valueOf(color));

        if(bodyBase64 != null){
            ItemStack content = ItemStackSerializer.fromBase64(bodyBase64);
            llama.getEquipment().setItem(EquipmentSlot.BODY, content);
        }

        if (inventoryBase64 != null) {
            llama.setCarryingChest(true); // 必ず先に設定
            ItemStack[] contents = ItemStackSerializer.fromBase64Array(inventoryBase64);
            llama.getInventory().setContents(contents);
        }
    }
}
