package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.entity.Player;
import org.bukkit.event.world.PortalCreateEvent;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetherPortalBuildObjective that = (NetherPortalBuildObjective) o;
        return getPlayer().equals(that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer());
    }
}
