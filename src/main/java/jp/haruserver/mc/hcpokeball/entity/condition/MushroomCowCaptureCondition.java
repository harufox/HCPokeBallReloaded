package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.MushroomCow;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class MushroomCowCaptureCondition implements CaptureCondition<MushroomCow>{
    @Override
    public boolean canCapture(MushroomCow mushroomCow, Player player) {
        return true;
    }
}