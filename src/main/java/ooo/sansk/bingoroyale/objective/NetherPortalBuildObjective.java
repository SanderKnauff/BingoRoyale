package ooo.sansk.bingoroyale.objective;

import org.bukkit.entity.Player;
import org.bukkit.event.world.PortalCreateEvent;

public class NetherPortalBuildObjective extends BingoObjective<PortalCreateEvent> {

    public NetherPortalBuildObjective(Player player) {
        super(player);
    }

    @Override
    public Class<PortalCreateEvent> getListenerType() {
        return PortalCreateEvent.class;
    }

    @Override
    public void checkCompleted(PortalCreateEvent portalCreateEvent) {
        if (!getPlayer().equals(portalCreateEvent.getEntity())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getDescription() {
        return "&7Build and light a &5Nether Portal";
    }
}
