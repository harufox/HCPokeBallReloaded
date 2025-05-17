package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Parrot;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class ParrotCaptureCondition implements CaptureCondition<Parrot>{
    @Override
    public boolean canCapture(Parrot cat, Player player) {
        //手懐け済みの場合はオーナーだけが捕獲可能
        if (cat.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID parrotOwnerUUID = cat.getOwnerUniqueId();
            return playerUUID.equals(parrotOwnerUUID);
        }
        //野生ならOK
        return true;
    }
}