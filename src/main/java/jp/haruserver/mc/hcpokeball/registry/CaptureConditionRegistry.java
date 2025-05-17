package jp.haruserver.mc.hcpokeball.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.bukkit.entity.EntityType;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;
import jp.haruserver.mc.hcpokeball.entity.condition.*;

public class CaptureConditionRegistry {
    private static final Map<EntityType, CaptureCondition> conditions = new HashMap<>();

    static {
        conditions.put(EntityType.WOLF, new WolfCaptureCondition());
        conditions.put(EntityType.CAT, new CatCaptureCondition());
        conditions.put(EntityType.HORSE, new HorseCaptureCondition());
        conditions.put(EntityType.PARROT, new ParrotCaptureCondition());
        conditions.put(EntityType.DONKEY, new DonkeyCaptureCondition());
        conditions.put(EntityType.CAMEL, new CamelCaptureCondition());
        conditions.put(EntityType.PANDA, new PandaCaptureCondition());
        conditions.put(EntityType.FOX, new FoxCaptureCondition());
        conditions.put(EntityType.LLAMA, new LlamaCaptureCondition());
        conditions.put(EntityType.GOAT, new GoatCaptureCondition());
        conditions.put(EntityType.COW, new CowCaptureCondition());
        conditions.put(EntityType.MOOSHROOM, new MushroomCowCaptureCondition());
        conditions.put(EntityType.CHICKEN, new ChickenCaptureCondition());
        conditions.put(EntityType.SHEEP, new SheepCaptureCondition());
        conditions.put(EntityType.PIG, new PigCaptureCondition());
        conditions.put(EntityType.RABBIT, new RabbitCaptureCondition());
        conditions.put(EntityType.AXOLOTL, new AxolotlCaptureCondition());
        conditions.put(EntityType.ARMADILLO, new ArmadilloCaptureCondition());
        conditions.put(EntityType.POLAR_BEAR, new PolarBearCaptureCondition());
        conditions.put(EntityType.FROG, new FrogCaptureCondition());
    }

    public static Optional<CaptureCondition> getCondition(EntityType type) {
        return Optional.ofNullable(conditions.get(type));
    }
}
