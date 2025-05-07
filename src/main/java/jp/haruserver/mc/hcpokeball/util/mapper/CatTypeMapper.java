package jp.haruserver.mc.hcpokeball.util.mapper;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Cat.Type;

public class CatTypeMapper {
    private static final Map<String, Type> stringToType = new HashMap<>();
    private static final Map<Type, String> TypeToString = new HashMap<>();

    static {
        stringToType.put("ALL_BLACK", Type.ALL_BLACK);
        stringToType.put("BLACK", Type.BLACK);
        stringToType.put("BRITISH_SHORTHAIR", Type.BRITISH_SHORTHAIR);
        stringToType.put("CALICO", Type.CALICO);
        stringToType.put("JELLIE", Type.JELLIE);
        stringToType.put("PERSIAN", Type.PERSIAN);
        stringToType.put("RAGDOLL", Type.RAGDOLL);
        stringToType.put("RED", Type.RED);
        stringToType.put("SIAMESE", Type.SIAMESE);
        stringToType.put("TABBY", Type.TABBY);
        stringToType.put("WHITE", Type.WHITE);

        for (Map.Entry<String, Type> entry : stringToType.entrySet()) {
            TypeToString.put(entry.getValue(), entry.getKey());
        }
    }

    public static Type fromString(String name) {
        return stringToType.getOrDefault(name, Type.WHITE);
    }

    public static String toString(Type catType) {
        return TypeToString.getOrDefault(catType, "WHITE");
    }
}
