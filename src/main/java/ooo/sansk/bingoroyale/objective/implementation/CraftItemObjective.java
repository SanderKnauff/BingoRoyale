package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.CraftItemEvent;

import java.util.Objects;

public class CraftItemObjective extends BingoObjective<CraftItemEvent> {

    private final Material itemType;
    private final int amountRequired;
    private int count;

    public CraftItemObjective(Player player, Material itemType, int amountRequired) {
        super(player);
        this.itemType = itemType;
        this.amountRequired = amountRequired;
        this.count = 0;
    }

    @Override
    public Class<CraftItemEvent> getListenerType() {
        return CraftItemEvent.class;
    }

    @Override
    public void checkCompleted(CraftItemEvent event) {
        if(!getPlayer().equals(event.getWhoClicked())) {
            return;
        }
        if (!this.itemType.equals(event.getRecipe().getResult().getType())) {
            return;
        }
        this.count = Math.min(this.count + event.getRecipe().getResult().getAmount(), amountRequired);
        if (this.count >= this.amountRequired) {
            setCompleted(true);
        }
    }

    @Override
    protected void setCompleted(boolean completed) {
        super.setCompleted(completed);
        if(!completed) {
            this.count = 0;
        }
    }

    @Override
    public String getDescription() {
        return String.format("&7Craft &c%d %s &8(%s%d&7/%s%d&8)",
                this.amountRequired,
                this.itemType.name(),
                isCompleted() ? "&a" : "&c",
                this.count,
                isCompleted() ? "&a" : "&c",
                this.amountRequired);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CraftItemObjective that = (CraftItemObjective) o;
        return amountRequired == that.amountRequired &&
                itemType == that.itemType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemType, amountRequired);
    }
}
