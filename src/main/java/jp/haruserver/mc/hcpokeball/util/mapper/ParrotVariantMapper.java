package jp.haruserver.mc.hcpokeball.util.mapper;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Parrot.Variant;

public class ParrotVariantMapper {
    private static final Map<String, Variant> stringToVariant = new HashMap<>();
    private static final Map<Variant, String> variantToString = new HashMap<>();

    static {
        stringToVariant.put("BLUE", Variant.BLUE);
        stringToVariant.put("CYAN", Variant.CYAN);
        stringToVariant.put("GRAY", Variant.GRAY);
        stringToVariant.put("GREEN", Variant.GREEN);
        stringToVariant.put("RED", Variant.RED);

        for (Map.Entry<String, Variant> entry : stringToVariant.entrySet()) {
            variantToString.put(entry.getValue(), entry.getKey());
        }
    }

    public static Variant fromString(String name) {
        return stringToVariant.getOrDefault(name, Variant.BLUE);
    }

    public static String toString(Variant variant) {
        return variantToString.getOrDefault(variant, "BLUE");
    }
}
