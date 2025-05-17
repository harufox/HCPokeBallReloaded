package jp.haruserver.mc.hcpokeball.serialize;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.registry.EntityDataRegistry;

public class EntityDeserializer {
    private static final Gson gson = new Gson();

    public static EntityData fromJson(String json) {
        JsonObject obj = JsonParser.parseString(json).getAsJsonObject();
        String type = obj.get("type").getAsString();

        Class<? extends EntityData> clazz = EntityDataRegistry.get(type);
        if (clazz == null) throw new IllegalArgumentException("Unknown entity type: " + type);

        return gson.fromJson(json, clazz);
    }
}