package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Camel;
import org.bukkit.entity.Entity;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.CamelData;

public class CamelCaptureHandler implements EntityCaptureHandler<Camel> {
    
    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Camel;
    }

    @Override
    public String serialize(Camel entity) {
        CamelData data = CamelData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.CAMEL_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, CamelData.class);
    }
}