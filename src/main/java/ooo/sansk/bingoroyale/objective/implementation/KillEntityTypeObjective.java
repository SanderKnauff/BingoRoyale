package ooo.sansk.bingoroyale.objective.implementation;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.util.TextFormatter;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

import java.util.Objects;

public class KillEntityTypeObjective extends BingoObjective<EntityDeathEvent> {

    private final EntityType entityType;
    private final int amountRequired;
    private int count;

    public KillEntityTypeObjective(Player player, EntityType entityType, int amountRequired) {
        super(player, Material.WOODEN_SWORD);
        this.entityType = entityType;
        this.amountRequired = amountRequired;
        this.count = 0;
    }

    @Override
    public Class<EntityDeathEvent> getListenerType() {
        return EntityDeathEvent.class;
    }

    @Override
    public void setCompleted(boolean completed) {
        super.setCompleted(completed);
        if(!completed) {
            this.count = 0;
        }
    }

    @Override
    public void checkCompleted(EntityDeathEvent event) {
        if(!entityType.equals(event.getEntityType())) {
            return;
        }
        if (!getPlayer().equals(event.getEntity().getKiller())){
            return;
        }
        this.count++;
        if (this.count >= this.amountRequired){
            setCompleted(true);
        } else {
            getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(getDescription()));
        }
    }

    @Override
    public String getName() {
        return "Kill " + amountRequired + " " + TextFormatter.enumNameToFancyString(entityType.name()) + (amountRequired == 1 ? "" : "s");
    }

    @Override
    public String getDescription() {
        return String.format("§7Kill §c%d %s%s §8(%s%d§7/%s%d§8)",
                this.amountRequired,
                TextFormatter.enumNameToFancyString(this.entityType.name()),
                this.amountRequired == 1 ? "" : "s",
                isCompleted() ? "§a" : "§c",
                this.count,
                isCompleted() ? "§a" : "§c",
                this.amountRequired);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KillEntityTypeObjective that = (KillEntityTypeObjective) o;
        return amountRequired == that.amountRequired &&
                entityType == that.entityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType, amountRequired);
    }
}
