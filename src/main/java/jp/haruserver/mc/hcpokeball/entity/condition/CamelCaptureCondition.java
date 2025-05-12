package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Camel;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class CamelCaptureCondition implements CaptureCondition<Camel>{
    @Override
    public boolean canCapture(Camel camel, Player player) {
        // 手懐け済みの場合はオーナーだけが捕獲可能
        UUID camelOwnerUUID = camel.getOwnerUniqueId();
        if (camelOwnerUUID != null) {
            UUID playerUUID = player.getUniqueId();
            return playerUUID.equals(camelOwnerUUID);
        }
        // 野生ならOK
        return true;
    }
}