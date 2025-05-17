package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Goat;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.GoatData;

public class GoatCaptureHandler implements EntityCaptureHandler<Goat> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Goat;
    }

    @Override
    public String serialize(Goat entity) {
        GoatData data = GoatData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.GOAT_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, GoatData.class);
    }
}