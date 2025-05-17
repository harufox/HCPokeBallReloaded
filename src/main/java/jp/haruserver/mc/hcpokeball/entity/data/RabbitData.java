package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class RabbitData implements EntityData {
    
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;

    //種族
    public String type;
    
    @Override
    public String getType() {
        return "RABBIT";
    }

    public static RabbitData fromEntity(Rabbit rabbit) {
        RabbitData data = new RabbitData();
        if(rabbit.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(rabbit.customName());
        }
        data.hasCustomName = rabbit.isCustomNameVisible();
        data.adult = rabbit.isAdult();
        data.health = rabbit.getHealth();
        data.type = rabbit.getRabbitType().toString();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Rabbit)) return;
        applyTo((Rabbit) entity,player);
    }

    public void applyTo(Rabbit rabbit,Player player) {
        if(customName != null){
            rabbit.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        rabbit.setCustomNameVisible(hasCustomName);
        if (adult) {
            rabbit.setAdult();
        } else {
            rabbit.setBaby();
        }

        rabbit.setRabbitType(Rabbit.Type.valueOf(type));

        rabbit.setHealth(health);

    }
}
