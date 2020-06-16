package ooo.sansk.bingoroyale.objective;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityBreedEvent;

public class BreedAnimalObjective extends BingoObjective<EntityBreedEvent> {

    private final EntityType entityType;

    public BreedAnimalObjective(Player player, EntityType entityType) {
        super(player);
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
    public String getDescription() {
        return "&7Breed a baby &c" + entityType.name();
    }
}
