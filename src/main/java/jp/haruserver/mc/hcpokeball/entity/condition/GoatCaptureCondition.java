package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Goat;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class GoatCaptureCondition implements CaptureCondition<Goat>{
    @Override
    public boolean canCapture(Goat goat, Player player) {
        return true;
    }
}