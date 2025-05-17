package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Armadillo;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class ArmadilloCaptureCondition implements CaptureCondition<Armadillo>{
    @Override
    public boolean canCapture(Armadillo armadillo, Player player) {
        //野生ならOK
        return true;
    }
}