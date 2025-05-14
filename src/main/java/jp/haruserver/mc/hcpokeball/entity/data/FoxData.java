package jp.haruserver.mc.hcpokeball.entity.data;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class FoxData implements EntityData {
    public String customName;
    public boolean adult;
    public boolean hasCustomName;
    public double health;
    public String variant;
    public String inventoryBase64;
    public String firstOwnerUUID;
    public String secondOwnerUUID;

    @Override
    public String getType() {
        return "FOX";
    }

    public static FoxData fromEntity(Fox fox) {
        FoxData data = new FoxData();
        if(fox.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(fox.customName());
        }

        AnimalTamer firstTamer = fox.getFirstTrustedPlayer();
        if(firstTamer != null){
            UUID firstOwnerUUID = firstTamer.getUniqueId();
            data.firstOwnerUUID = firstOwnerUUID.toString();

            AnimalTamer secondTamer = fox.getSecondTrustedPlayer();
            if(secondTamer != null){
                UUID secondOwnerUUID = secondTamer.getUniqueId();
                data.secondOwnerUUID = secondOwnerUUID.toString();
            }

        }
        data.hasCustomName = fox.isCustomNameVisible();
        data.adult = fox.isAdult();
        data.health = fox.getHealth();
        data.variant = fox.getFoxType().toString();
        ItemStack holdingItem = fox.getEquipment().getItemInMainHand();

        if(holdingItem != null){
            data.inventoryBase64 = ItemStackSerializer.toBase64(holdingItem);
        }
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
       if (!(entity instanceof Fox)) return;
        applyTo((Fox) entity,player);
    }
    
    public void applyTo(Fox fox,Player player) {
        if(customName != null){
            fox.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        fox.setCustomNameVisible(hasCustomName);
        if(adult){
            fox.setAdult();
        }else{
            fox.setBaby();
        }
        fox.setFoxType(Fox.Type.valueOf(variant));
        fox.setHealth(health);

        if(inventoryBase64 != null){
            ItemStack contents = ItemStackSerializer.fromBase64(inventoryBase64);
            fox.getEquipment().setItemInMainHand(contents);
        }

        if(firstOwnerUUID != null){
            fox.setSitting(true);
            fox.setFirstTrustedPlayer(Bukkit.getServer().getOfflinePlayer(UUID.fromString(firstOwnerUUID)));
            if(secondOwnerUUID != null){
                fox.setSecondTrustedPlayer(Bukkit.getServer().getOfflinePlayer(UUID.fromString(secondOwnerUUID)));
            }
        }
    }
}
