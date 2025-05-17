package jp.haruserver.mc.hcpokeball.util.mapper;

import org.bukkit.entity.Cow.Variant;

import java.util.HashMap;
import java.util.Map;

public class CowVariantMapper {
    private static final Map<String, Variant> stringToVariant = new HashMap<>();
    private static final Map<Variant, String> variantToString = new HashMap<>();

    static {
        stringToVariant.put("COLD", Variant.COLD);
        stringToVariant.put("TEMPERATE", Variant.TEMPERATE);
        stringToVariant.put("WARM", Variant.WARM);

        for (Map.Entry<String, Variant> entry : stringToVariant.entrySet()) {
            variantToString.put(entry.getValue(), entry.getKey());
        }
    }

    public static Variant fromString(String name) {
        return stringToVariant.getOrDefault(name, Variant.COLD);
    }

    public static String toString(Variant variant) {
        return variantToString.getOrDefault(variant, "COLD");
    }
}
