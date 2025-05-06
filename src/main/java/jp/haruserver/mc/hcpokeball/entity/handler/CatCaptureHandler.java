package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Entity;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.CatData;

public class CatCaptureHandler implements EntityCaptureHandler<Cat>{

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Cat;
    }

    @Override
    public String serialize(Cat entity) {
        CatData data = CatData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.CAT_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, CatData.class);
    }
}
