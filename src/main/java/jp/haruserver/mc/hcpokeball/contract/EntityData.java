package jp.haruserver.mc.hcpokeball.contract;

import org.bukkit.entity.Entity;

public interface EntityData {
    String getType();
    void applyTo(Entity entity);
}
