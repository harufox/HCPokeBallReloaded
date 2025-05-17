package jp.haruserver.mc.hcpokeball.entity.condition;

import org.bukkit.entity.Axolotl;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.CaptureCondition;

public class AxolotlCaptureCondition implements CaptureCondition<Axolotl>{
    @Override
    public boolean canCapture(Axolotl axolotl, Player player) {
        //野生ならOK
        return true;
    }
}