package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Parrot;
import org.bukkit.entity.Entity;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.ParrotData;

public class ParrotCaptureHandler implements EntityCaptureHandler<Parrot>{

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Parrot;
    }

    @Override
    public String serialize(Parrot entity) {
        ParrotData data = ParrotData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.PARROT_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, ParrotData.class);
    }
}
