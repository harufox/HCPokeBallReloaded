package jp.haruserver.mc.hcpokeball.registry;

import java.util.HashMap;
import java.util.Map;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.CamelData;
import jp.haruserver.mc.hcpokeball.entity.data.CatData;
import jp.haruserver.mc.hcpokeball.entity.data.DonkeyData;
import jp.haruserver.mc.hcpokeball.entity.data.HorseData;
import jp.haruserver.mc.hcpokeball.entity.data.ParrotData;
import jp.haruserver.mc.hcpokeball.entity.data.WolfData;

public class EntityDataRegistry {
    private static final Map<String, Class<? extends EntityData>> registry = new HashMap<>();

    static {
        register("WOLF", WolfData.class);
        register("CAT", CatData.class);
        register("PARROT", ParrotData.class);
        register("HORSE", HorseData.class);
        register("CAMEL", CamelData.class);
        register("DONKEY", DonkeyData.class);
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
