package jp.haruserver.mc.hcpokeball.contract;

import org.bukkit.Material;
import org.bukkit.entity.Entity;

public interface EntityCaptureHandler<T extends Entity> {
    //捕獲対象のエンティティか判定
    boolean supports(Entity entity);
    //シリアライズ化しJSONに変換
    String serialize(T entity);
    //JSONからEntityへデシリアライズする
    EntityData deserialize(String json);
    //見た目のスポーンエッグ指定
    Material getEggMaterial();
}