package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Donkey;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class DonkeyCaptureCondition implements CaptureCondition<Donkey>{
    @Override
    public boolean canCapture(Donkey donkey, Player player) {
        // 手懐け済みの場合はオーナーだけが捕獲可能
        if (donkey.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID donkeyOwnerUUID = donkey.getOwnerUniqueId();
            return playerUUID.equals(donkeyOwnerUUID);
        }
        // 野生ならOK
        return true;
    }
}