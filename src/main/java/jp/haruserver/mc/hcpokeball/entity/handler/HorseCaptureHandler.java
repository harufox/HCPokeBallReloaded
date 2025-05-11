package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.HorseData;

public class HorseCaptureHandler implements EntityCaptureHandler<Horse> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Horse;
    }

    @Override
    public String serialize(Horse entity) {
        HorseData data = HorseData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.HORSE_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, HorseData.class);
    }
}