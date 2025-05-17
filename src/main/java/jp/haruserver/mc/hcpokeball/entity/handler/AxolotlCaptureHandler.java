package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Axolotl;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.AxolotlData;

public class AxolotlCaptureHandler implements EntityCaptureHandler<Axolotl> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Axolotl;
    }

    @Override
    public String serialize(Axolotl entity) {
        AxolotlData data = AxolotlData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.AXOLOTL_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, AxolotlData.class);
    }
}