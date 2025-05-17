package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Rabbit;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class RabbitCaptureCondition implements CaptureCondition<Rabbit>{
    @Override
    public boolean canCapture(Rabbit rabbit, Player player) {
        return true;
    }
}