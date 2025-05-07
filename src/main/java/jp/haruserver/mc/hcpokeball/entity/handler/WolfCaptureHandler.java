package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Wolf;
import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.WolfData;

public class WolfCaptureHandler implements EntityCaptureHandler<Wolf> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Wolf;
    }

    @Override
    public String serialize(Wolf entity) {
        WolfData data = WolfData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.WOLF_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, WolfData.class);
    }
}