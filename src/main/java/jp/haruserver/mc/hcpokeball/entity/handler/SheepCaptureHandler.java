package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.SheepData;

public class SheepCaptureHandler implements EntityCaptureHandler<Sheep> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Sheep;
    }

    @Override
    public String serialize(Sheep entity) {
        SheepData data = SheepData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.SHEEP_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, SheepData.class);
    }
}