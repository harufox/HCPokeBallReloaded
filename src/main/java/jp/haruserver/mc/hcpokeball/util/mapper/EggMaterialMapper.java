package jp.haruserver.mc.hcpokeball.util.mapper;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashMap;
import java.util.Map;

public class EggMaterialMapper {
    private static final Map<EntityType, Material> eggMap = new HashMap<>();

    static {
        eggMap.put(EntityType.ALLAY, Material.ALLAY_SPAWN_EGG);
        eggMap.put(EntityType.AXOLOTL, Material.AXOLOTL_SPAWN_EGG);
        eggMap.put(EntityType.BAT, Material.BAT_SPAWN_EGG);
        eggMap.put(EntityType.BEE, Material.BEE_SPAWN_EGG);
        eggMap.put(EntityType.BLAZE, Material.BLAZE_SPAWN_EGG);
        eggMap.put(EntityType.CAMEL, Material.CAMEL_SPAWN_EGG);
        eggMap.put(EntityType.CAT, Material.CAT_SPAWN_EGG);
        eggMap.put(EntityType.CAVE_SPIDER, Material.CAVE_SPIDER_SPAWN_EGG);
        eggMap.put(EntityType.CHICKEN, Material.CHICKEN_SPAWN_EGG);
        eggMap.put(EntityType.COD, Material.COD_SPAWN_EGG);
        eggMap.put(EntityType.COW, Material.COW_SPAWN_EGG);
        eggMap.put(EntityType.CREEPER, Material.CREEPER_SPAWN_EGG);
        eggMap.put(EntityType.DOLPHIN, Material.DOLPHIN_SPAWN_EGG);
        eggMap.put(EntityType.DONKEY, Material.DONKEY_SPAWN_EGG);
        eggMap.put(EntityType.DROWNED, Material.DROWNED_SPAWN_EGG);
        eggMap.put(EntityType.ELDER_GUARDIAN, Material.ELDER_GUARDIAN_SPAWN_EGG);
        eggMap.put(EntityType.ENDERMAN, Material.ENDERMAN_SPAWN_EGG);
        eggMap.put(EntityType.ENDERMITE, Material.ENDERMITE_SPAWN_EGG);
        eggMap.put(EntityType.EVOKER, Material.EVOKER_SPAWN_EGG);
        eggMap.put(EntityType.FOX, Material.FOX_SPAWN_EGG);
        eggMap.put(EntityType.FROG, Material.FROG_SPAWN_EGG);
        eggMap.put(EntityType.GHAST, Material.GHAST_SPAWN_EGG);
        eggMap.put(EntityType.GLOW_SQUID, Material.GLOW_SQUID_SPAWN_EGG);
        eggMap.put(EntityType.GOAT, Material.GOAT_SPAWN_EGG);
        eggMap.put(EntityType.GUARDIAN, Material.GUARDIAN_SPAWN_EGG);
        eggMap.put(EntityType.HOGLIN, Material.HOGLIN_SPAWN_EGG);
        eggMap.put(EntityType.HORSE, Material.HORSE_SPAWN_EGG);
        eggMap.put(EntityType.HUSK, Material.HUSK_SPAWN_EGG);
        eggMap.put(EntityType.IRON_GOLEM, Material.IRON_GOLEM_SPAWN_EGG);
        eggMap.put(EntityType.LLAMA, Material.LLAMA_SPAWN_EGG);
        eggMap.put(EntityType.MAGMA_CUBE, Material.MAGMA_CUBE_SPAWN_EGG);
        eggMap.put(EntityType.MULE, Material.MULE_SPAWN_EGG);
        eggMap.put(EntityType.MOOSHROOM, Material.MOOSHROOM_SPAWN_EGG);
        eggMap.put(EntityType.OCELOT, Material.OCELOT_SPAWN_EGG);
        eggMap.put(EntityType.PANDA, Material.PANDA_SPAWN_EGG);
        eggMap.put(EntityType.PARROT, Material.PARROT_SPAWN_EGG);
        eggMap.put(EntityType.PHANTOM, Material.PHANTOM_SPAWN_EGG);
        eggMap.put(EntityType.PIG, Material.PIG_SPAWN_EGG);
        eggMap.put(EntityType.PIGLIN, Material.PIGLIN_SPAWN_EGG);
        eggMap.put(EntityType.PIGLIN_BRUTE, Material.PIGLIN_BRUTE_SPAWN_EGG);
        eggMap.put(EntityType.PILLAGER, Material.PILLAGER_SPAWN_EGG);
        eggMap.put(EntityType.POLAR_BEAR, Material.POLAR_BEAR_SPAWN_EGG);
        eggMap.put(EntityType.PUFFERFISH, Material.PUFFERFISH_SPAWN_EGG);
        eggMap.put(EntityType.RABBIT, Material.RABBIT_SPAWN_EGG);
        eggMap.put(EntityType.RAVAGER, Material.RAVAGER_SPAWN_EGG);
        eggMap.put(EntityType.SALMON, Material.SALMON_SPAWN_EGG);
        eggMap.put(EntityType.SHEEP, Material.SHEEP_SPAWN_EGG);
        eggMap.put(EntityType.SHULKER, Material.SHULKER_SPAWN_EGG);
        eggMap.put(EntityType.SILVERFISH, Material.SILVERFISH_SPAWN_EGG);
        eggMap.put(EntityType.SKELETON, Material.SKELETON_SPAWN_EGG);
        eggMap.put(EntityType.SKELETON_HORSE, Material.SKELETON_HORSE_SPAWN_EGG);
        eggMap.put(EntityType.SLIME, Material.SLIME_SPAWN_EGG);
        eggMap.put(EntityType.SNIFFER, Material.SNIFFER_SPAWN_EGG);
        eggMap.put(EntityType.SNOW_GOLEM, Material.SNOW_GOLEM_SPAWN_EGG);
        eggMap.put(EntityType.SPIDER, Material.SPIDER_SPAWN_EGG);
        eggMap.put(EntityType.SQUID, Material.SQUID_SPAWN_EGG);
        eggMap.put(EntityType.STRAY, Material.STRAY_SPAWN_EGG);
        eggMap.put(EntityType.STRIDER, Material.STRIDER_SPAWN_EGG);
        eggMap.put(EntityType.TADPOLE, Material.TADPOLE_SPAWN_EGG);
        eggMap.put(EntityType.TRADER_LLAMA, Material.TRADER_LLAMA_SPAWN_EGG);
        eggMap.put(EntityType.TROPICAL_FISH, Material.TROPICAL_FISH_SPAWN_EGG);
        eggMap.put(EntityType.TURTLE, Material.TURTLE_SPAWN_EGG);
        eggMap.put(EntityType.VEX, Material.VEX_SPAWN_EGG);
        eggMap.put(EntityType.VILLAGER, Material.VILLAGER_SPAWN_EGG);
        eggMap.put(EntityType.VINDICATOR, Material.VINDICATOR_SPAWN_EGG);
        eggMap.put(EntityType.WANDERING_TRADER, Material.WANDERING_TRADER_SPAWN_EGG);
        eggMap.put(EntityType.WARDEN, Material.WARDEN_SPAWN_EGG);
        eggMap.put(EntityType.WITCH, Material.WITCH_SPAWN_EGG);
        eggMap.put(EntityType.WITHER_SKELETON, Material.WITHER_SKELETON_SPAWN_EGG);
        eggMap.put(EntityType.WOLF, Material.WOLF_SPAWN_EGG);
        eggMap.put(EntityType.ZOGLIN, Material.ZOGLIN_SPAWN_EGG);
        eggMap.put(EntityType.ZOMBIE, Material.ZOMBIE_SPAWN_EGG);
        eggMap.put(EntityType.ZOMBIE_HORSE, Material.ZOMBIE_HORSE_SPAWN_EGG);
        eggMap.put(EntityType.ZOMBIE_VILLAGER, Material.ZOMBIE_VILLAGER_SPAWN_EGG);
        eggMap.put(EntityType.ZOMBIFIED_PIGLIN, Material.ZOMBIFIED_PIGLIN_SPAWN_EGG);
    }

    public static Material getEggMaterial(EntityType type) {
        return eggMap.getOrDefault(type, Material.EGG); // fallback
    }
}