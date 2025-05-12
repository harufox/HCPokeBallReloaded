package jp.haruserver.mc.hcpokeball.contract;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface CaptureCondition<T extends Entity>{
    //捕獲できるか判定
    boolean canCapture(T entity, Player player);
}