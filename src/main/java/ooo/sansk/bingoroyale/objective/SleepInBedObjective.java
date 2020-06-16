package ooo.sansk.bingoroyale.objective;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerBedEnterEvent;

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
}
