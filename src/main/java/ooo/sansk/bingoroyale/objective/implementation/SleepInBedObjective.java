package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;

import java.util.Objects;

public class SleepInBedObjective extends BingoObjective<PlayerBedEnterEvent> {

    private final Material bedType;

    public SleepInBedObjective(Player player, Material bedType) {
        super(player);
        this.bedType = bedType;
    }

    @Override
    public Class<PlayerBedEnterEvent> getListenerType() {
        return PlayerBedEnterEvent.class;
    }

    @Override
    public void checkCompleted(PlayerBedEnterEvent event) {
        if (!getPlayer().equals(event.getPlayer())) {
            return;
        }
        if (bedType != null && !bedType.equals(event.getBed().getType())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getDescription() {
        return String.format("&7Sleep in a %sbed",
                bedType != null ? createReadableBedName() + " " : "");
    }

    private String createReadableBedName() {
        return bedType.name().toLowerCase().replace("BED", "").replace("_", " ");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SleepInBedObjective that = (SleepInBedObjective) o;
        return bedType == that.bedType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bedType);
    }
}
