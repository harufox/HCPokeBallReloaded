package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Frog;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class FrogCaptureCondition implements CaptureCondition<Frog>{
    @Override
    public boolean canCapture(Frog frog, Player player) {
        return true;
    }
}