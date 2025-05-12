package jp.haruserver.mc.hcpokeball.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.EntityType;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.WolfCaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.CatCaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.HorseCaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.ParrotCaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.DonkeyCaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.CamelCaptureCondition;

public class CaptureConditionRegistry {
    private static final Map<EntityType, CaptureCondition> conditions = new HashMap<>();

    static {
        conditions.put(EntityType.WOLF, new WolfCaptureCondition());
        conditions.put(EntityType.CAT, new CatCaptureCondition());
        conditions.put(EntityType.HORSE, new HorseCaptureCondition());
        conditions.put(EntityType.PARROT, new ParrotCaptureCondition());
        conditions.put(EntityType.DONKEY, new DonkeyCaptureCondition());
        conditions.put(EntityType.CAMEL, new CamelCaptureCondition());
    }

    public static Optional<CaptureCondition> getCondition(EntityType type) {
        return Optional.ofNullable(conditions.get(type));
    }
}
