package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.MushroomCow;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.MushroomCowData;

public class MushroomCowCaptureHandler implements EntityCaptureHandler<MushroomCow> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof MushroomCow;
    }

    @Override
    public String serialize(MushroomCow entity) {
        MushroomCowData data = MushroomCowData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.MOOSHROOM_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, MushroomCowData.class);
    }
}