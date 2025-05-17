package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class HorseCaptureCondition implements CaptureCondition<Horse>{
    @Override
    public boolean canCapture(Horse horse, Player player) {
        //手懐け済みの場合はオーナーだけが捕獲可能
        if (horse.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID horseOwnerUUID = horse.getOwnerUniqueId();
            return playerUUID.equals(horseOwnerUUID);
        }
        //野生ならOK
        return true;
    }
}