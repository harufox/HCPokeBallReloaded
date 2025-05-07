package jp.haruserver.mc.hcpokeball.serialize;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jp.haruserver.mc.hcpokeball.contract.EntityData;

public class EntitySerializer {
    private static final Gson gson = new GsonBuilder().create();

    public static String serialize(EntityData data) {
        return gson.toJson(data);
    }

    public static <T extends EntityData> T deserialize(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
