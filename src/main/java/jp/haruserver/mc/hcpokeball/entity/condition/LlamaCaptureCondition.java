package jp.haruserver.mc.hcpokeball.entity.condition;

import java.util.UUID;

import org.bukkit.entity.Llama;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class LlamaCaptureCondition implements CaptureCondition<Llama>{
    @Override
    public boolean canCapture(Llama llama, Player player) {
        // 手懐け済みの場合はオーナーだけが捕獲可能
        if (llama.isTamed()) {
            UUID playerUUID = player.getUniqueId();
            UUID llamaOwnerUUID = llama.getOwnerUniqueId();
            return playerUUID.equals(llamaOwnerUUID);
        }
        // 野生ならOK
        return true;
    }
}