package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.PolarBear;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.PolarBearData;

public class PolarBearCaptureHandler implements EntityCaptureHandler<PolarBear> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof PolarBear;
    }

    @Override
    public String serialize(PolarBear entity) {
        PolarBearData data = PolarBearData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.POLAR_BEAR_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, PolarBearData.class);
    }
}