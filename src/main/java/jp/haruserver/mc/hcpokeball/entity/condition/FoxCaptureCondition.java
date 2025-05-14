package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Fox;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class FoxCaptureCondition  implements CaptureCondition<Fox>{
    @Override
    public boolean canCapture(Fox fox, Player player) {
        // 手懐け済みの場合はオーナーだけが捕獲可能
        AnimalTamer tamer = fox.getFirstTrustedPlayer();
        if (tamer != null) {
            UUID playerUUID = player.getUniqueId();
            AnimalTamer firstTamer = fox.getFirstTrustedPlayer();
            if(firstTamer != null){
                UUID foxFirstOwnerUUID = firstTamer.getUniqueId();
                if (foxFirstOwnerUUID.equals(playerUUID)){
                    return true;
                }
                AnimalTamer secondTamer = fox.getSecondTrustedPlayer();
                if(secondTamer != null){
                    UUID foxSecondOwnerUUID = secondTamer.getUniqueId();
                    if (foxSecondOwnerUUID.equals(playerUUID)){
                        return true;
                    }
                }
            }
            return false;
        }
        // 野生ならOK
        return true;
    }
}
