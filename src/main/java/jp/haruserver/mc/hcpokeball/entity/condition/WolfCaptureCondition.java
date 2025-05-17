package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class WolfCaptureCondition implements CaptureCondition<Wolf> {
    @Override
    public boolean canCapture(Wolf wolf, Player player) {
        //手懐け済みの場合はオーナーだけが捕獲可能
        if (wolf.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID wolfOwnerUUID = wolf.getOwnerUniqueId();
            return playerUUID.equals(wolfOwnerUUID);
        }
        //野生ならOK
        return true;
    }
}