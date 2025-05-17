package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Frog;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.FrogData;

public class FrogCaptureHandler implements EntityCaptureHandler<Frog> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Frog;
    }

    @Override
    public String serialize(Frog entity) {
        FrogData data = FrogData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.FROG_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, FrogData.class);
    }
}