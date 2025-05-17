package jp.haruserver.mc.hcpokeball.registry;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.*;

import java.util.HashMap;
import java.util.Map;

public class CaptureHandlerRegistry {
    private static final Map<EntityType, EntityCaptureHandler<? extends Entity>> handlerMap = new HashMap<>();

    static {
        handlerMap.put(EntityType.WOLF, new WolfCaptureHandler());
        handlerMap.put(EntityType.CAT, new CatCaptureHandler());
        handlerMap.put(EntityType.PARROT, new ParrotCaptureHandler());
        handlerMap.put(EntityType.HORSE, new HorseCaptureHandler());
        handlerMap.put(EntityType.CAMEL, new CamelCaptureHandler());
        handlerMap.put(EntityType.DONKEY, new DonkeyCaptureHandler());
        handlerMap.put(EntityType.PANDA, new PandaCaptureHandler());
        handlerMap.put(EntityType.FOX, new FoxCaptureHandler());
        handlerMap.put(EntityType.LLAMA, new LlamaCaptureHandler());
        handlerMap.put(EntityType.GOAT, new GoatCaptureHandler());
        handlerMap.put(EntityType.COW, new CowCaptureHandler());
        handlerMap.put(EntityType.MOOSHROOM, new MushroomCowCaptureHandler());
        handlerMap.put(EntityType.CHICKEN, new ChickenCaptureHandler());
        handlerMap.put(EntityType.SHEEP, new SheepCaptureHandler());
        handlerMap.put(EntityType.PIG, new PigCaptureHandler());
        handlerMap.put(EntityType.RABBIT, new RabbitCaptureHandler());
        handlerMap.put(EntityType.AXOLOTL, new AxolotlCaptureHandler());
        handlerMap.put(EntityType.ARMADILLO, new ArmadilloCaptureHandler());
        handlerMap.put(EntityType.POLAR_BEAR, new PolarBearCaptureHandler());
        handlerMap.put(EntityType.FROG, new FrogCaptureHandler());
    }

    public static EntityCaptureHandler<? extends Entity> getHandler(EntityType type) {
        return handlerMap.get(type);
    }

    public static boolean isSupported(EntityType type) {
        return handlerMap.containsKey(type);
    }
}
