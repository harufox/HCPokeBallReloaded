package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.PolarBear;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class PolarBearCaptureCondition implements CaptureCondition<PolarBear>{
    @Override
    public boolean canCapture(PolarBear polarBear, Player player) {
        return true;
    }
}