package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import ooo.sansk.bingoroyale.util.TextFormatter;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityBreedEvent;

import java.util.Objects;

public class BreedAnimalObjective extends BingoObjective<EntityBreedEvent> {

    private final EntityType entityType;

    public BreedAnimalObjective(Player player, EntityType entityType) {
        super(player, Material.WHEAT);
        this.entityType = entityType;
    }

    @Override
    public Class<EntityBreedEvent> getListenerType() {
        return EntityBreedEvent.class;
    }

    @Override
    public void checkCompleted(EntityBreedEvent event) {
        if(!entityType.equals(event.getEntityType())) {
            return;
        }
        if (!getPlayer().equals(event.getBreeder())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getName() {
        return "Breed " + TextFormatter.enumNameToFancyString(entityType.name()) + "s";
    }

    @Override
    public String getDescription() {
        return "§7Breed a baby §c" + TextFormatter.enumNameToFancyString(entityType.name());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BreedAnimalObjective that = (BreedAnimalObjective) o;
        return entityType == that.entityType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(entityType);
    }
}
