package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Armadillo;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.ArmadilloData;

public class ArmadilloCaptureHandler implements EntityCaptureHandler<Armadillo> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Armadillo;
    }

    @Override
    public String serialize(Armadillo entity) {
        ArmadilloData data = ArmadilloData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.ARMADILLO_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, ArmadilloData.class);
    }
}