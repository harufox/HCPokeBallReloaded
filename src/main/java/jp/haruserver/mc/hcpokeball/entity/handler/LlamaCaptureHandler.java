package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.entity.Entity;
import org.bukkit.Material;
import org.bukkit.entity.Llama;

import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.LlamaData;

public class LlamaCaptureHandler implements EntityCaptureHandler<Llama> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Llama;
    }

    @Override
    public String serialize(Llama entity) {
        LlamaData data = LlamaData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.LLAMA_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, LlamaData.class);
    }
}