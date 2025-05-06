package jp.haruserver.mc.hcpokeball.util;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Egg;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import jp.haruserver.mc.hcpokeball.HCPokeBall;

public class PokeBallKeys {

    //オーナーUUID
    public final NamespacedKey OWNER_UUID;
    //ペットのEntityType
    public final NamespacedKey ENTITY_TYPE;
    //ペットの情報を格納したNBTString
    public final NamespacedKey NBT_STRING;

    public PokeBallKeys(HCPokeBall plugin) {
        this.OWNER_UUID = new NamespacedKey(plugin, "hc_pokeball_owner_uuid");
        this.ENTITY_TYPE = new NamespacedKey(plugin, "hc_pokeball_entity_type");
        this.NBT_STRING = new NamespacedKey(plugin, "hc_pokeball_nbt_string");
    }

    //オーナーUUIDが存在するか確認する
    public boolean hasOwnerUUID(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(OWNER_UUID, PersistentDataType.STRING);
    }

    //オーナーUUID getter
    public String getOwnerUUID(ItemStack item) {
        if (!hasOwnerUUID(item)) return null;
        return item.getItemMeta().getPersistentDataContainer().get(OWNER_UUID, PersistentDataType.STRING);
    }

    //オーナーUUID setter
    public void setOwnerUUID(ItemStack item, String uuid) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(OWNER_UUID, PersistentDataType.STRING, uuid);
        item.setItemMeta(meta);
    }

    ///projectile用オーナーUUIDが存在するか確認する
    public boolean hasProjectileOwnerUUID(Egg egg) {
        return egg.getPersistentDataContainer().has(OWNER_UUID, PersistentDataType.STRING);
    }

    //projectile用オーナーUUID getter
    public String getProjectileOwnerUUID(Egg egg) {
        return egg.getPersistentDataContainer().get(OWNER_UUID, PersistentDataType.STRING);
    }

    //projectile用オーナーUUID setter
    public void setProjectileOwnerUUID(Egg egg, String uuid) {
        PersistentDataContainer container = egg.getPersistentDataContainer();
        container.set(OWNER_UUID, PersistentDataType.STRING, uuid);
    }

    //オーナーUUIDが存在するか確認する
    public boolean hasEntityType(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(ENTITY_TYPE, PersistentDataType.STRING);
    }

    //オーナーUUID getter
    public String getEntityType(ItemStack item) {
        if (!hasOwnerUUID(item)) return null;
        return item.getItemMeta().getPersistentDataContainer().get(ENTITY_TYPE, PersistentDataType.STRING);
    }

    //オーナーUUID setter
    public void setEntityType(ItemStack item, String uuid) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(ENTITY_TYPE, PersistentDataType.STRING, uuid);
        item.setItemMeta(meta);
    }

    ///projectile用オーナーUUIDが存在するか確認する
    public boolean hasProjectileEntityType(Egg egg) {
        return egg.getPersistentDataContainer().has(ENTITY_TYPE, PersistentDataType.STRING);
    }

    //projectile用オーナーUUID getter
    public String getProjectileEntityType(Egg egg) {
        return egg.getPersistentDataContainer().get(ENTITY_TYPE, PersistentDataType.STRING);
    }

    //projectile用オーナーUUID setter
    public void setProjectileEntityType(Egg egg, String uuid) {
        PersistentDataContainer container = egg.getPersistentDataContainer();
        container.set(ENTITY_TYPE, PersistentDataType.STRING, uuid);
    }


    //ペット情報NBT文字列が存在するか確認する
    public boolean hasNbtString(ItemStack item) {
        return item != null && item.hasItemMeta() && item.getItemMeta().getPersistentDataContainer().has(NBT_STRING, PersistentDataType.STRING);
    }

    //ペット情報NBT文字列 getter
    public String getNbtString(ItemStack item) {
        if (!hasOwnerUUID(item)) return null;
        return item.getItemMeta().getPersistentDataContainer().get(NBT_STRING, PersistentDataType.STRING);
    }

    //ペット情報NBT文字列 setter
    public void setNbtString(ItemStack item, String nbtString) {
        ItemMeta meta = item.getItemMeta();
        PersistentDataContainer container = meta.getPersistentDataContainer();
        container.set(NBT_STRING, PersistentDataType.STRING, nbtString);
        item.setItemMeta(meta);
    }

    ///projectile用ペット情報NBT文字列が存在するか確認する
    public boolean hasProjectileNbtString(Egg egg) {
        return egg.getPersistentDataContainer().has(NBT_STRING, PersistentDataType.STRING);
    }

    //projectile用ペット情報NBT文字列 getter
    public String getProjectileNbtString(Egg egg) {
        return egg.getPersistentDataContainer().get(NBT_STRING, PersistentDataType.STRING);
    }

    //projectile用ペット情報NBT文字列 setter
    public void setProjectileNbtString(Egg egg, String nbtString) {
        PersistentDataContainer container = egg.getPersistentDataContainer();
        container.set(NBT_STRING, PersistentDataType.STRING, nbtString);
    }

}
