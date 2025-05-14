package jp.haruserver.mc.hcpokeball.entity.data;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.util.ItemStackSerializer;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Color;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class HorseData implements EntityData {

    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;
    public double maxHealth;
    public double jumpStrength;
    public boolean isTamed;
    public String color;
    public String style;
    public String inventoryBase64;

    @Override
    public String getType() {
        return "HORSE";
    }

    public static HorseData fromEntity(Horse horse) {
        HorseData data = new HorseData();
        if(horse.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(horse.customName());
        }
        data.hasCustomName = horse.isCustomNameVisible();
        data.adult = horse.isAdult();
        data.health = horse.getHealth();
        data.isTamed = horse.isTamed();
        data.color = horse.getColor().toString();
        data.style = horse.getStyle().toString();
        data.maxHealth = horse.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
        data.jumpStrength = horse.getJumpStrength();
        ItemStack[] contents = horse.getInventory().getContents();
        data.inventoryBase64 = ItemStackSerializer.toBase64Array(contents);
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Horse)) return;
        applyTo((Horse) entity,player);
    }

    public void applyTo(Horse horse,Player player) {
        if(customName != null){
            horse.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        horse.setCustomNameVisible(hasCustomName);
        if (adult) {
            horse.setAdult();
        } else {
            horse.setBaby();
        }
        horse.setTamed(isTamed);
        horse.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        horse.setHealth(health);
        horse.setJumpStrength(jumpStrength);
        horse.setColor(Color.valueOf(color));
        horse.setStyle(Style.valueOf(style));

        ItemStack[] contents = ItemStackSerializer.fromBase64Array(inventoryBase64);
        horse.getInventory().setContents(contents);
        horse.setOwner(player);
    }
}
