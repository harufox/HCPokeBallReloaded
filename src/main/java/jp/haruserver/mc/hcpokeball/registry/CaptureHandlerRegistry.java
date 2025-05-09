package jp.haruserver.mc.hcpokeball.registry;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.CatCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.WolfCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.ParrotCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.HorseCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.CamelCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.DonkeyCaptureHandler;
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
    }

    public static EntityCaptureHandler<? extends Entity> getHandler(EntityType type) {
        return handlerMap.get(type);
    }

    public static boolean isSupported(EntityType type) {
        return handlerMap.containsKey(type);
    }
}
