package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Sheep;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class SheepCaptureCondition implements CaptureCondition<Sheep>{
    @Override
    public boolean canCapture(Sheep sheep, Player player) {
        return true;
    }
}