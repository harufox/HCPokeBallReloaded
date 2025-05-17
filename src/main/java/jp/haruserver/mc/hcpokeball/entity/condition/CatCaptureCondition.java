package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class CatCaptureCondition implements CaptureCondition<Cat>{
    @Override
    public boolean canCapture(Cat cat, Player player) {
        //手懐け済みの場合はオーナーだけが捕獲可能
        if (cat.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID catOwnerUUID = cat.getOwnerUniqueId();
            return playerUUID.equals(catOwnerUUID);
        }
        //野生ならOK
        return true;
    }
}