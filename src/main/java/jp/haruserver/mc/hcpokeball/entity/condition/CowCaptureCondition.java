package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Cow;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class CowCaptureCondition implements CaptureCondition<Cow>{
    @Override
    public boolean canCapture(Cow cow, Player player) {
        return true;
    }
}