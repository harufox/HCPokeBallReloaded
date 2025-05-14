package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Fox;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.FoxData;

public class FoxCaptureHandler implements EntityCaptureHandler<Fox> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Fox;
    }

    @Override
    public String serialize(Fox entity) {
        FoxData data = FoxData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.FOX_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, FoxData.class);
    }
}