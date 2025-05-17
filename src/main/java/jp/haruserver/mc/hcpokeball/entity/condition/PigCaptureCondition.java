package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class PigCaptureCondition implements CaptureCondition<Pig>{
    @Override
    public boolean canCapture(Pig pig, Player player) {
        return true;
    }
}