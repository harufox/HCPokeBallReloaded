package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Chicken;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class ChickenCaptureCondition implements CaptureCondition<Chicken>{
    @Override
    public boolean canCapture(Chicken chicken, Player player) {
        return true;
    }
}