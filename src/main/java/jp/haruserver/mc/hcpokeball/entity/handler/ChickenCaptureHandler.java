package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Chicken;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.ChickenData;

public class ChickenCaptureHandler implements EntityCaptureHandler<Chicken> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Chicken;
    }

    @Override
    public String serialize(Chicken entity) {
        ChickenData data = ChickenData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.CHICKEN_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, ChickenData.class);
    }
}