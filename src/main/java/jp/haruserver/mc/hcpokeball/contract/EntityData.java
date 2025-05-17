package jp.haruserver.mc.hcpokeball.contract;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public interface EntityData {
    //Typeの文字列を返す
    String getType();
    //デシリアライズした情報をentityに反映する
    void applyTo(Entity entity,Player player);
}
