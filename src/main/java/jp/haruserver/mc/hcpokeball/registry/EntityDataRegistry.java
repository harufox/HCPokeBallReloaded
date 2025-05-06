package jp.haruserver.mc.hcpokeball.registry;

import java.util.HashMap;
import java.util.Map;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.WolfData;

public class EntityDataRegistry {
    private static final Map<String, Class<? extends EntityData>> registry = new HashMap<>();

    static {
        register("WOLF", WolfData.class);
    }

    public static void register(String type, Class<? extends EntityData> clazz) {
        registry.put(type.toUpperCase(), clazz);
    }

    public static Class<? extends EntityData> get(String type) {
        return registry.get(type.toUpperCase());
    }

    public static boolean contains(String type) {
        return registry.containsKey(type.toUpperCase());
    }
}
