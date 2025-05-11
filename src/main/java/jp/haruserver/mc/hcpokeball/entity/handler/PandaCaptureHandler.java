package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Panda;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.PandaData;

public class PandaCaptureHandler implements EntityCaptureHandler<Panda> {
    
    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Panda;
    }

    @Override
    public String serialize(Panda entity) {
        PandaData data = PandaData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.PANDA_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, PandaData.class);
    }
}