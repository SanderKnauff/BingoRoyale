package ooo.sansk.bingoroyale.objective.implementation;

import ooo.sansk.bingoroyale.objective.BingoObjective;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerPortalEvent;

import java.util.Objects;

public class NetherPortalEnterObjective extends BingoObjective {

    public NetherPortalEnterObjective(Player player) {
        super(player, Material.OBSIDIAN);
    }

    @Override
    public boolean listensFor(Object event) {
        return event instanceof PlayerPortalEvent;
    }

    @Override
    public void checkCompleted(Object event) {
        PlayerPortalEvent playerPortalEvent = (PlayerPortalEvent) event;
        if (!getPlayer().equals(playerPortalEvent.getPlayer())) {
            return;
        }
        setCompleted(true);
    }

    @Override
    public String getName() {
        return "Create Nether Portal";
    }

    @Override
    public String getDescription() {
        return "§7Enter a §5Nether Portal";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NetherPortalEnterObjective that = (NetherPortalEnterObjective) o;
        return getPlayer().equals(that.getPlayer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer());
    }
}
