package ooo.sansk.bingoroyale.objective;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDeathEvent;

public class KillEntityTypeObjective extends BingoObjective<EntityDeathEvent> {

    private final EntityType entityType;
    private final int amountRequired;
    private int count;

    public KillEntityTypeObjective(Player player, EntityType entityType, int amountRequired) {
        super(player);
        this.entityType = entityType;
        this.amountRequired = amountRequired;
        this.count = 0;
    }

    @Override
    public Class<EntityDeathEvent> getListenerType() {
        return EntityDeathEvent.class;
    }

    @Override
    protected void setCompleted(boolean completed) {
        super.setCompleted(completed);
        if(!completed) {
            this.count = 0;
        }
    }

    @Override
    public void checkCompleted(EntityDeathEvent entityDeathEvent) {
        if(!entityType.equals(entityDeathEvent.getEntityType())) {
            return;
        }
        if (!getPlayer().equals(entityDeathEvent.getEntity().getKiller())){
            return;
        }
        this.count++;
        if (this.count >= this.amountRequired){
            setCompleted(true);
        }
    }

    @Override
    public String getDescription() {
        return String.format("%s&7Kill &c%d %s &8(%s%d&7/%s%d&8)",
                isCompleted() ? "&8[&aCOMPLETED&8] " : "",
                this.amountRequired,
                this.entityType.name(),
                isCompleted() ? "&a" : "&c",
                this.count,
                isCompleted() ? "&a" : "&c",
                this.amountRequired);
    }
}
