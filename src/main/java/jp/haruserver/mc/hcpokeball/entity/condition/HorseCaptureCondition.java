package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class HorseCaptureCondition implements CaptureCondition<Horse>{
    @Override
    public boolean canCapture(Horse horse, Player player) {
        // 手懐け済か確認する
        if (horse.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID horseOwnerUUID = horse.getOwnerUniqueId();
            //オーナーかどうか判定
            return playerUUID.equals(horseOwnerUUID);
        }
        // 野生ならOK
        return true;
    }
}