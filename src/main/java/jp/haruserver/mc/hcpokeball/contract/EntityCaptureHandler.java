package jp.haruserver.mc.hcpokeball.contract;

import org.bukkit.Material;
import org.bukkit.entity.Entity;

public interface EntityCaptureHandler<T extends Entity> {
    boolean supports(Entity entity);            // 対象のエンティティか判定
    String serialize(T entity);                 // JSONに変換
    EntityData deserialize(String json); // JSONからEntityへ復元（スポーン含む）
    Material getEggMaterial();                  // 見た目（スポーンエッグMaterial）
}