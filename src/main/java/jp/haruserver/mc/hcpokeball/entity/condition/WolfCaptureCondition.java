package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Wolf;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class WolfCaptureCondition implements CaptureCondition {
    @Override
    public boolean canCapture(Entity entity, Player player) {
        if (entity instanceof Wolf wolf) {
            // 手懐け済みの場合はオーナーだけが捕獲可能
            if (wolf.isTamed()) {
                return player.getUniqueId().equals(wolf.getOwnerUniqueId());
            }
            // 野生ならOK（かつ大人）
            return wolf.isAdult();
        }
        return false;
    }
}