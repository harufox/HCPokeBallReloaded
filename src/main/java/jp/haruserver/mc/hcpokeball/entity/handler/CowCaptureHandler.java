package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Cow;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.CowData;

public class CowCaptureHandler implements EntityCaptureHandler<Cow> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Cow;
    }

    @Override
    public String serialize(Cow entity) {
        CowData data = CowData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.COW_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, CowData.class);
    }
}