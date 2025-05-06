package jp.haruserver.mc.hcpokeball.registry;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import jp.haruserver.mc.hcpokeball.contract.EntityCaptureHandler;
import jp.haruserver.mc.hcpokeball.entity.handler.WolfCaptureHandler;

import java.util.HashMap;
import java.util.Map;

public class CaptureHandlerRegistry {
    private static final Map<EntityType, EntityCaptureHandler<? extends Entity>> handlerMap = new HashMap<>();

    static {
        handlerMap.put(EntityType.WOLF, new WolfCaptureHandler());
    }

    public static EntityCaptureHandler<? extends Entity> getHandler(EntityType type) {
        return handlerMap.get(type);
    }

    public static boolean isSupported(EntityType type) {
        return handlerMap.containsKey(type);
    }
}
