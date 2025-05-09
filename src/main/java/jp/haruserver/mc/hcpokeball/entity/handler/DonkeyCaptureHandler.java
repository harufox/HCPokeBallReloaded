package jp.haruserver.mc.hcpokeball.entity.handler;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Donkey;
import com.google.gson.Gson;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.DonkeyData;

public class DonkeyCaptureHandler implements EntityCaptureHandler<Donkey> {

    @Override
    public boolean supports(Entity entity) {
        return entity instanceof Donkey;
    }

    @Override
    public String serialize(Donkey entity) {
        DonkeyData data = DonkeyData.fromEntity(entity);
        return new Gson().toJson(data);
    }

    @Override
    public Material getEggMaterial() {
        return Material.DONKEY_SPAWN_EGG;
    }
    @Override
    public EntityData deserialize(String json) {
        return new Gson().fromJson(json, DonkeyData.class);
    }
}