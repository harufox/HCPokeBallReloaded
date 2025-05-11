package jp.haruserver.mc.hcpokeball.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.EntityType;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.WolfCaptureCondition;

public class CaptureConditionRegistry {
    private static final Map<EntityType, CaptureCondition> conditions = new HashMap<>();

    static {
        conditions.put(EntityType.WOLF, new WolfCaptureCondition());
    }

    public static Optional<CaptureCondition> getCondition(EntityType type) {
        return Optional.ofNullable(conditions.get(type));
    }
}
