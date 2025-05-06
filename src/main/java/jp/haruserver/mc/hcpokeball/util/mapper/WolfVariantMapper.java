package jp.haruserver.mc.hcpokeball.util.mapper;

import org.bukkit.entity.Wolf.Variant;

import java.util.HashMap;
import java.util.Map;

public class WolfVariantMapper {
    private static final Map<String, Variant> stringToVariant = new HashMap<>();
    private static final Map<Variant, String> variantToString = new HashMap<>();

    static {
        stringToVariant.put("ASHEN", Variant.ASHEN);
        stringToVariant.put("BLACK", Variant.BLACK);
        stringToVariant.put("CHESTNUT", Variant.CHESTNUT);
        stringToVariant.put("PALE", Variant.PALE);
        stringToVariant.put("RUSTY", Variant.RUSTY);
        stringToVariant.put("SNOWY", Variant.SNOWY);
        stringToVariant.put("SPOTTED", Variant.SPOTTED);
        stringToVariant.put("STRIPED", Variant.STRIPED);
        stringToVariant.put("WOODS", Variant.WOODS);

        for (Map.Entry<String, Variant> entry : stringToVariant.entrySet()) {
            variantToString.put(entry.getValue(), entry.getKey());
        }
    }

    public static Variant fromString(String name) {
        return stringToVariant.getOrDefault(name, Variant.PALE);
    }

    public static String toString(Variant variant) {
        return variantToString.getOrDefault(variant, "PALE");
    }
}
