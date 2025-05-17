package jp.haruserver.mc.hcpokeball.registry;

import java.util.HashMap;
import java.util.Map;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import jp.haruserver.mc.hcpokeball.entity.data.*;

public class EntityDataRegistry {
    private static final Map<String, Class<? extends EntityData>> registry = new HashMap<>();

    static {
        register("WOLF", WolfData.class);
        register("CAT", CatData.class);
        register("PARROT", ParrotData.class);
        register("HORSE", HorseData.class);
        register("CAMEL", CamelData.class);
        register("DONKEY", DonkeyData.class);
        register("PANDA", PandaData.class);
        register("FOX", FoxData.class);
        register("LLAMA", LlamaData.class);
        register("GOAT", GoatData.class);
        register("COW", CowData.class);
        register("MOOSHROOM", MushroomCowData.class);
        register("CHICKEN", ChickenData.class);
        register("SHEEP", SheepData.class);
        register("PIG", PigData.class);
        register("RABBIT", RabbitData.class);
        register("AXOLOTL", AxolotlData.class);
        register("ARMADILLO", ArmadilloData.class);
        register("POLAR_BEAR", PolarBearData.class);
        register("FROG", FrogData.class);
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
