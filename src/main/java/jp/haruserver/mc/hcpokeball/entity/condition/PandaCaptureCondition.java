package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Panda;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class PandaCaptureCondition implements CaptureCondition<Panda>{
    @Override
    public boolean canCapture(Panda panda, Player player) {
        return true;
    }
}