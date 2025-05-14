package jp.haruserver.mc.hcpokeball.entity.data;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Panda;
import org.bukkit.entity.Panda.Gene;
import org.bukkit.entity.Player;

import jp.haruserver.mc.hcpokeball.contract.EntityData;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

public class PandaData implements EntityData {
      
    public String customName;
    public boolean hasCustomName;
    public boolean adult;
    public double health;
    public double maxHealth;
    public String mainGene;
    public String hiddenGene;

    @Override
    public String getType() {
        return "PANDA";
    }

    public static PandaData fromEntity(Panda panda) {
        PandaData data = new PandaData();
        if(panda.customName() != null){
            data.customName = LegacyComponentSerializer.legacySection().serialize(panda.customName());
        }
        data.hasCustomName = panda.isCustomNameVisible();
        data.adult = panda.isAdult();
        data.mainGene = panda.getMainGene().toString();
        data.hiddenGene = panda.getHiddenGene().toString();
        data.maxHealth = panda.getAttribute(Attribute.MAX_HEALTH).getBaseValue();
        data.health = panda.getHealth();
        return data;
    }

    @Override
    public void applyTo(Entity entity,Player player) {
        if (!(entity instanceof Panda)) return;
        applyTo((Panda) entity,player);
    }

    public void applyTo(Panda panda,Player player) {
        if(customName != null){
            panda.customName(LegacyComponentSerializer.legacySection().deserialize(customName));
        }
        panda.setCustomNameVisible(hasCustomName);
        if (adult) {
            panda.setAdult();
        } else {
            panda.setBaby();
        }
        panda.setHiddenGene(Gene.valueOf(hiddenGene));
        panda.setMainGene(Gene.valueOf(mainGene));
        panda.getAttribute(Attribute.MAX_HEALTH).setBaseValue(maxHealth);
        panda.setHealth(health);
    }
}
