package jp.haruserver.mc.hcpokeball.contract;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface EntityData {
    String getType();
    void applyTo(Entity entity,Player player);
}
